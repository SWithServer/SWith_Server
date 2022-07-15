//작성자: 이준표
//Notification 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "NOTIFICATION")
public class Notification {
    @Id
    @Column(name = "NOTIFICATION_IDX")
    private long notificationIdx;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    private String notificationContent;

    //Aquerytool에 없었음
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
