//작성자: 이준표
//Notification 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTIFICATION")
public class Notification extends BaseTimeEntity {
    @Id
//    @Column(name = "NOTIFICATION_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationIdx;

    private String notificationContent;

    @ManyToOne
    @JoinColumn//(name = "USER_IDX")
    private User user;
}
