//작성자: 이준표
//Interest 엔티티 JPA 객체 매핑핑
//createdAt 22.07.14
//updaredAt 22.07.14
package com.example.demo.src.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "INTEREST")
public class Interest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTEREST_IDX")
    private Byte interestIdx;

    private long userIdx;


}
