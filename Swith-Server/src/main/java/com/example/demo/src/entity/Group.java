//작성자: 이준표
//Group 엔티티 JPA 객체 매핑
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
@Table(name = "GROUP")
public class Group {

    @Id
    private long groupIdx;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    private String groupImgUrl;
    private String title;
    private Byte meet;
    private Byte frequency;
    private String periods;
    private Byte online;
    private long regionIdx1;
    private long regionIdx2;

    @ManyToOne
    @JoinColumn(name = "INTEREST_IDX")      //
    private Interest interest;

    private String topic;
    private Byte memberLimit;           // 2 ~ 10명
    private Byte applicationMethod;     // 지원 방식

    @Temporal(TemporalType.TIMESTAMP)
    private Date recruitmentEndDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date groupStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date groupEnd;

    //오타 수정
    private Byte attendanceValidTime;
    //Content -> groupContent
    private String groupContent;
    private Byte status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    //UserGroup 일대다 양방향
    //
    @OneToMany(mappedBy = "group")
    private List<UserGroup> userGroups = new ArrayList<UserGroup>();

    //Session 일대다 양방향
    //Announcement와 일대다 양방향
    //Application과 일대다 양방향
    //Session과 일대다 양방향
}
