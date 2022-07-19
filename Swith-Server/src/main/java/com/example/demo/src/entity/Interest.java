//작성자: 이준표
//Interest 엔티티 JPA 객체 매핑
//createdAt 22.07.14
//updatedAt 22.07.19
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
    @Column(columnDefinition = "TINYINT")
    private Integer interestIdx;

    private String interestContent;
}
