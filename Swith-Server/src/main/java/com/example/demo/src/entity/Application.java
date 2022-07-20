//작성자: 이준표
//Application 엔티티 JPA 객체 매핑
//createdAt 22.07.15
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
@Table(name = "APPLICATION")
public class Application extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationIdx;

    //다대일 양방향
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

    //다대일 양방향
    @ManyToOne
    @JoinColumn(name = "groupIdx")
    private GroupInfo groupInfo;

    @Column(columnDefinition = "TINYINT")
    @Builder.Default
    private Integer status = 0;

    @Column(length = 200)
    private String applicationContent;
}
