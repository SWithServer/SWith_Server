//작성자: 이준표
//Announcement 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement {
    @Id
    @Column(name = "ANNOUNCEMENT_IDX")
    private long announcementIdx;


    @ManyToOne
    @JoinColumn(name = "GROUP_IDX")
    private GroupInfo groupInfo;

    private String announcementContent;

    private Byte status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
