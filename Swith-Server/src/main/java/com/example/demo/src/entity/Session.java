//작성자: 이준표
//Session 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updatedAt 22.07.19
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SESSION")
public class Session extends BaseTimeEntity {
    @Id
//    @Column(name = "SESSION_IDX")
    private Long sessionIdx;

    @ManyToOne
    @JoinColumn//(name = "GROUP_INFO_IDX")
    private GroupInfo groupInfo;
    private Integer sessionNum;

    //어떤 자료형으로 할지 고민이 필요
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionEnd;

    @Column(columnDefinition = "TINYINT")
    private Integer online;

    private String place;

    @Column(columnDefinition = "TINYINT")
    @Builder.Default
    private Integer status = 0;

    private String sessionContent;

    //Attendance과 일대다 양방향
    //Session의 출석부를 불러올 때 사용할듯
    @OneToMany(mappedBy = "session")
    private List<Attendance> attendances = new ArrayList<>();
}
