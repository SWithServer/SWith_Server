//작성자: 이준표
//Interest 엔티티 JPA 객체 매핑
//createdAt 22.07.14
//updaredAt 22.07.18
package com.example.demo.src.entity;


import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INTEREST")
public class Interest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTEREST_IDX", columnDefinition = "TINYINT")
    private Integer interestIdx;

    private String interestContent;
}
