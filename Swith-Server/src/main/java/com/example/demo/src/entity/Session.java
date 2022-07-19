//작성자: 이준표
//Session 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SESSION")
public class Session {
    @Id
    @Column(name = "SESSION_IDX")
    private long sessionIdx;

    @ManyToOne
    @JoinColumn(name = "GROUP_IDX")
    private GroupInfo groupInfo;

    private long sessionNum;
    private Date sessionStart;
    private Date sessionEnd;
    private Byte online;
    private String place;
    private Byte status;
    private String sessionContent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    //Attendance과 연결 (양방향)
}
