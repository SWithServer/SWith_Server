package com.example.demo.src.repository;

import com.example.demo.src.dto.request.GetGroupInfoSearchReq;
import com.example.demo.src.dto.response.GetGroupInfoSearchRes;
//import com.example.demo.src.dto.response.QGetGroupInfoSearchRes;
import com.example.demo.src.entity.Interest;
import com.example.demo.src.entity.QGroupInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

import static com.example.demo.src.entity.QApplication.application;
import static com.example.demo.src.entity.QGroupInfo.groupInfo;


@Repository
@RequiredArgsConstructor
//GroupInfoRepository
//GroupInfoRepositoryImpl
//GroupInfoRepositoryCustom
public class GroupInfoRepositoryImpl implements GroupInfoRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QGroupInfo groupInfoSub = new QGroupInfo("groupInfoSub");
    @Override
    public Slice<GetGroupInfoSearchRes> searchGroupInfo(GetGroupInfoSearchReq searchCond, Pageable pageable) {

        //커버링 인덱스 전략
        List<Long> ids = queryFactory
                .select(groupInfo.groupIdx)
                .from(groupInfo)
                .where(Read(searchCond.getSortCond(),
                        searchCond.getGroupIdx(),
                        searchCond.getClientTime()))
                .limit(pageable.getPageSize())
                .fetch();


        List<GetGroupInfoSearchRes> content =
                queryFactory
                .select(Projections.fields(GetGroupInfoSearchRes.class,
                        groupInfo.groupIdx,
                        groupInfo.title,
                        groupInfo.groupContent,
                        groupInfo.regionIdx1,
                        groupInfo.regionIdx2,
                        groupInfo.recruitmentEndDate,
                        groupInfo.memberLimit,
                        groupInfo.createdAt,
                        ExpressionUtils.as(
                                JPAExpressions.select(application.count()).from(application)
                                        .where(application.groupInfo.groupIdx.eq(groupInfo.groupIdx),
                                                application.status.eq(1)),"NumOfApplicants")
                        ))
                .from(groupInfo)
                        .where(
                                Search(searchCond),
                                groupInfo.groupIdx.in(ids)
                        )

                .groupBy(groupInfo.groupIdx)
                .limit(pageable.getPageSize()+1) // limit보다 데이터를 1개 더 들고와서, 해당 데이터가 있다면 hasNext 변수에 true를 넣어 알림
                        .orderBy((OrderSpecifier<?>) Sort(searchCond.getSortCond()))
                        .fetch();
                        //.orderBy(new OrderSpecifier(Order.ASC,groupInfo.recruitmentEndDate);
        System.out.println(content);

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content, pageable, hasNext);

    }



    private BooleanBuilder Search(GetGroupInfoSearchReq searchCond){
        BooleanBuilder builder = new BooleanBuilder();
        BooleanBuilder builder2 = new BooleanBuilder(); // and ( or ) and // or 괄호용
        builder.and(titlecontain(searchCond.getTitle()));
        if(searchCond.getRegionIdx() != null){

            //builder.or(groupInfo.regionIdx1.eq(searchCond.getRegionIdx()));
            //builder.or(groupInfo.regionIdx2.eq(searchCond.getRegionIdx()));
            //regionIdx string으로 바뀌면 startWith 쓰먼됨.
            builder2.or(groupInfo.regionIdx1.startsWith(searchCond.getRegionIdx()));
            builder2.or(groupInfo.regionIdx2.startsWith(searchCond.getRegionIdx()));
            builder.and(builder2);
        }
        //builder.or(interestEq(searchCond.getInterest1()));
        //builder.or(interestEq(searchCond.getInterest2()));

        builder.and(groupInfo.interest.interestIdx.in(searchCond.getInterest1(), searchCond.getInterest2()));
        return builder;

    }


    private BooleanExpression titlecontain(String title){
        System.out.println(title);
        return hasText(title) ? groupInfo.title.contains(title) :null;

    }

//    private BooleanExpression regionIn(Long regionIdx){
//        if(regionIdx == null){
//            return null;
//        }
//        return groupInfo.regionIdx1.in(regionIdx).or(groupInfo.regionIdx2.in(regionIdx));
//
//    }

    private BooleanExpression interestEq(Integer i){
        System.out.println("진입");
        if(i == null){
            System.out.println("null이래.interestEq"+i);
            return null;
        }

        System.out.println("뭐가 잡혔다");
        System.out.println("interestEq"+i);
        return groupInfo.interest.interestIdx.eq(i);


    }
    private BooleanExpression Read(Integer sortCond, Long groupIdx, LocalDateTime now){

        if(groupIdx != null){
            if(sortCond == 0){ //마감일
                return groupInfo.recruitmentEndDate.gt(
                        JPAExpressions.select(groupInfoSub.recruitmentEndDate)
                                .from(groupInfoSub)
                                .where(groupInfoSub.groupIdx.eq(groupIdx)));
            }
            return groupInfo.createdAt.gt(
                    JPAExpressions.select(groupInfoSub.createdAt)
                            .from(groupInfoSub)
                            .where(groupInfoSub.groupIdx.eq(groupIdx)));
        }
        //첫 번째 페이지
        System.out.println("첫 페이지 ");
        System.out.println(now);
        System.out.println(LocalDate.from(now));
        if(sortCond == 0 && groupIdx == null){
            System.out.println("마감순");
            return groupInfo.recruitmentEndDate.gt(LocalDate.from(now));
        }
        return groupInfo.createdAt.gt(now);

    }
    private Object Sort(Integer sortCond){
        if(sortCond == 0){
            return groupInfo.recruitmentEndDate.asc();
        }
        return groupInfo.createdAt.asc();
    }


    public JPAQuery<Integer> searchtestGroup(GetGroupInfoSearchReq searchCond, Pageable pageable){

        JPAQuery<Integer> numOfApplicants = queryFactory
                .select(application.status.count().intValue())
                .from(application)
                .where( application.groupInfo.groupIdx.eq(searchCond.getGroupIdx()),
                        application.status.eq(1));
        return numOfApplicants;


    }


}
