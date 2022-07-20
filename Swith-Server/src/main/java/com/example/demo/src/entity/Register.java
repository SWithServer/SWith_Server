//작성자: 이준표
//UserGroup 엔티티 JPA 객체 매핑
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
@Table(name = "REGISTER")
public class Register extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registerIdx;

    //FK 2개
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

    @ManyToOne
    @JoinColumn(name = "groupIdx")
    private GroupInfo groupInfo;

    @Column(columnDefinition = "TINYINT")
    @Builder.Default
    private Integer status = 0;
}
