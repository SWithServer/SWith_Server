//작성자: 이준표
//Rating 엔티티 JPA 객체 매핑
//createdAt 22.07.14
//updatedAt 22.07.19
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RATING")
public class Rating extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingIdx;

    //평가자를 기준으로 조회할일은 없기에 매핑할 이유가 없음
    private Long raterIdx;

    //User 단방향
    @ManyToOne
    @JoinColumn(name = "rateeIdx")
    private User user;

    //별점(1~5점)
    @Column(columnDefinition = "TINYINT")
    private Integer star;
}
