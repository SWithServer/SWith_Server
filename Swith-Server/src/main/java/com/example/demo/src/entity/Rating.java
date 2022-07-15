//작성자: 이준표
//Rating 엔티티 JPA 객체 매핑
//createdAt 22.07.14
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "RATING")
public class Rating {
    @Id
    @Column(name = "RATING_IDX")
    private long ratingIdx;

    //평가자를 기준으로 조회할일은 없기에 매핑할 이유가 없음
    private long raterIdx;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    //별점(1~5점)
    private Byte star;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
