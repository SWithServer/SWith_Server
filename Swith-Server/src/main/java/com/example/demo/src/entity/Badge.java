//작성자: 이준표
//Badge 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updatedAt 22.07.19
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BADGE")
public class Badge extends BaseTimeEntity {

    @Id
//    @Column(name = "BADGE_IDX")
    private Long badgeIdx;

    @ManyToOne
    @JoinColumn//(name = "USER_IDX")
    private User user;

    //Double로 저장하는게 좋을듯
    private Double attendanceRate;

    //Interest 다대일 단방향
    @ManyToOne
    @JoinColumn//(name = "INTEREST_IDX")
    private Interest interest;
}
