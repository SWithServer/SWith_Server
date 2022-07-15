//작성자: 이준표
//Badge 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BADGE")
public class Badge {

    @Id
    @Column(name = "BADGE_IDX")
    private long badgeIdx;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    private long attendanceRate;

    @ManyToOne
    @JoinColumn(name = "INTEREST_IDX")
    private Interest interest;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
