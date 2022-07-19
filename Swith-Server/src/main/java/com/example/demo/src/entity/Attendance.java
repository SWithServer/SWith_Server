//작성자: 이준표
//Attendance 엔티티 JPA 객체 매핑
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
@Table(name = "ATTENDANCE")
public class Attendance extends BaseTimeEntity {
    @Id
    @Column(name = "ATTENDANCE_IDX")
    private Long attendanceIdx;

    //이건 양방향으로 할 필요가 없음, 다대일 단방향으로
    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    //Session 다대일 양방향
    @ManyToOne
    @JoinColumn(name = "SESSION_IDX")
    private Session session;

    @Column(columnDefinition = "TINYINT")
    @Builder.Default
    private Integer status = 0;
}
