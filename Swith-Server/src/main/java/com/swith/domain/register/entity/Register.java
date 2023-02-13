package com.swith.domain.register.entity;


import com.swith.domain.application.entity.Application;
import com.swith.domain.groupinfo.entity.GroupInfo;
import com.swith.domain.common.BaseTimeEntity;
import com.swith.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REGISTER")
public class Register extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registerIdx;

    @ManyToOne(fetch = LAZY) // N : 1 양방향
    @JoinColumn(name = "userIdx")
    private User user; //스터디에 가입된 유저 Idx

    @ManyToOne(fetch = LAZY)// N : 1 단방향
    @JoinColumn(name = "groupIdx")
    private GroupInfo groupInfo; //유저가 가입한 스터디 Idx

    @Column(columnDefinition = "TINYINT")
    @Builder.Default
    private Integer status = 0; //0:가입 1:탈퇴

    private Integer rating; //받은 평가 점수
    @Builder.Default
    private Integer rating_status = 0; //본인이 평가를 완료를 했는지의 여부.






    public static Register toEntity(Application application,Integer status){
        return Register.builder()
                .user(application.getUser())
                .groupInfo(application.getGroupInfo())
                .status(status)
                .build();

    }

    public void changeStatus(Integer status){
        this.status = status;

    }






}
