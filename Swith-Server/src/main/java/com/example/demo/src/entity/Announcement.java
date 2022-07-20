//작성자: 이준표
//Announcement 엔티티 JPA 객체 매핑
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
@Table(name = "ANNOUNCEMENT")
public class Announcement extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long announcementIdx;

    @ManyToOne
    @JoinColumn(name = "groupIdx")
    private GroupInfo groupInfo;

    @Column(length = 200)
    private String announcementContent;

    @Column(columnDefinition = "TINYINT")
    @Builder.Default
    private Integer status = 0;
}
