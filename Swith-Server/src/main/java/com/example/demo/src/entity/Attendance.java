//작성자: 이준표
//Attendance 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ATTENDANCE")
public class Attendance {
    @Id
    @Column(name = "ATTENDANCE_IDX")
    private long attendanceIdx;

    //이건 양방향으로 할 필요가 없음, 다대일 단방향으로
    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SESSION_IDX")
    private Session session;

    private Byte status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
