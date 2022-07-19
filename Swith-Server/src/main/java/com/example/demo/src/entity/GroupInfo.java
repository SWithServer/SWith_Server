//작성자: 이준표
//Group 엔티티 JPA 객체 매핑
//createdAt 22.07.14
//updaredAt 22.07.18
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GROUP_INFO")
public class GroupInfo extends BaseTimeEntity {

    @Id
    private long groupInfoIdx;

    // 관리자
    @ManyToOne
    @JoinColumn(name = "ADMIN_IDX")
    private User user;

    private String groupImgUrl;
    private String title;

    // 스터디 예정 주기    0: 주별 1: 월별 2: 자유
    @Column(columnDefinition = "TINYINT")
    private Integer meet;

    // 주/월별 스터디 횟수 (meet가 0,1일때만 값을 저장)
    @Column(columnDefinition = "TINYINT")
    private Integer frequency;

    // meet가 2일 경우 예정 주기에 대한 자유로운 문자열 입력
    private String periods;

    // 0: 오프라인 1: 온라인
    @Column(columnDefinition = "TINYINT")
    private Integer online;

    // 활동 예정 지역코드 2개 (온라인값이 1이면 둘 다 NULL)
    private long regionIdx1;
    private long regionIdx2;

    // 스터디 분류
    @ManyToOne
    @JoinColumn(name = "INTEREST_IDX")
    private Interest interest;

    // 스터디 주제
    private String topic;

    // 스터디 정원 2 ~ 10명
    @Column(columnDefinition = "TINYINT")
    private Integer memberLimit;

    // 지원 방식
    private Integer applicationMethod;

    // 모집 기간 종료일
    @Temporal(TemporalType.TIMESTAMP)
    private Date recruitmentEndDate;

    // 스터디 활동 시작예정일
    @Temporal(TemporalType.TIMESTAMP)
    private Date groupStart;

    // 스터디 활동 종료예정일
    @Temporal(TemporalType.TIMESTAMP)
    private Date groupEnd;

    // 오타 수정: Vaild -> Valid
    // 출석 유효 시간 (10분 단위)
    @Column(columnDefinition = "TINYINT")
    private Integer attendanceValidTime;

    // 컬럼명 수정: Content -> groupContent
    // 스터디에 대한 학습 예정 내용
    private String groupContent;

    // 스터디 상태
    @Column(columnDefinition = "TINYINT")
    private Integer status;

    //UserGroup 일대다 양방향
    //
    @OneToMany(mappedBy = "groupInfo")
    private List<UserGroup> userGroups = new ArrayList<UserGroup>();

    //Session 일대다 양방향
    //Announcement와 일대다 양방향
    //Application과 일대다 양방향
    //Session과 일대다 양방향
}
