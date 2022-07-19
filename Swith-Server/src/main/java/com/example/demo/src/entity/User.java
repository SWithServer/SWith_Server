//작성자: 이준표
//User 엔티티 JPA 객체 매핑
//createdAt 22.07.14
//updaredAt 22.07.18
package com.example.demo.src.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User extends BaseTimeEntity {
    //@Id는 PK를 의미, @GeneratedValue 어노테이션은 기본키를 설정하는 전략으로
    //아래와 같이 설정하면 DB에 위임하는 방식(AUTO_INCREMENT)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_IDX")
    private Long userIdx;


    private String email;
    private String password;

    //다대일 단방향
    @ManyToOne()
    @JoinColumn(name = "INTEREST_IDX1")
    private Interest interest1;

    //다대일 단방향
    @ManyToOne()
    @JoinColumn(name = "INTEREST_IDX2")
    private Interest interest2;

    private String introduction;
    private String profileImgUrl;
    private double averageStar;

    //DB에서는 TINYINT 타입으로 저장
    @Column(columnDefinition = "TINYINT")
    @Builder.Default
    private Integer status = 0;


    //Rating 일대다 양방향(유저에 대한 평가 리스트)
    @OneToMany(mappedBy = "user")
    private List<Rating> ratings = new ArrayList<Rating>();

    //UserGroup 일대다 양방향
    //가입한 그룹을 불러올 때 쓰일 것
    @OneToMany(mappedBy = "user")
    private List<UserGroup> userGroups = new ArrayList<UserGroup>();

    //Badge 일대다 양방향
    //프로필 조회시 사용할 것
    @OneToMany(mappedBy = "user")
    private List<Badge> badges = new ArrayList<Badge>();

    //notification 일대다 양방향
    //알림 조회시
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<Notification>();

    //Application 일대다 양방향
    //자신의 지원서 목록 조회시? 필요한지는 모르겠음
    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<Application>();


    //유저가 가입한 스터디에서의 개인 출석율은 쿼리를 써야할 듯
}
