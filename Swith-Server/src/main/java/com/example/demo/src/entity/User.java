//작성자: 이준표
//User 엔티티 JPA 객체 매핑
//createdAt 22.07.14
//updaredAt 22.07.15
package com.example.demo.src.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {
    //@Id는 PK를 의미, @GeneratedValue 어노테이션은 기본키를 설정하는 전략으로
    //아래와 같이 설정하면 DB에 위임하는 방식(AUTO_INCREMENT)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_IDX")
    private long userIdx;

    private String email;
    private String password;

    //다대일 단방향
    @ManyToOne()
    @JoinColumn(name = "INTEREST_IDX")
    private Interest interest1;

    //다대일 단방향
//    @ManyToOne()
//    @JoinColumn(name = "INTEREST_IDX")
//    private Interest interest2;

    private String introduction;
    private String profileImgUrl;
    private double averageStar;

    private Byte status;

    //날짜+시각을 나타내기 위해 DB에서 TIMESTAMP로 타입 설정
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    //Rating 일대다 양방향(유저에 대한 평가 리스트)
    @OneToMany(mappedBy = "user" )
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
    //자신의 지원서 목록 조회시?
    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<Application>();


    //유저가 가입한 스터디에서의 개인 출석율은 쿼리를 써야할 듯
}
