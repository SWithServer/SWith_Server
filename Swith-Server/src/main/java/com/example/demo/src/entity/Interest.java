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

    @Column( length = 45)
    private String interestContent; //분류의 이름(카테고리)





}
