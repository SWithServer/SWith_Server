//작성자: 이준표
//Application 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "APPLICATION")
public class Application {
    @Id
    @Column(name = "APPLICATION_IDX")
    private long applicationIdx;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    @ManyToOne
    @JoinColumn(name = "GROUP_IDX")
    private GroupInfo groupInfo;

    private Byte status;
    private String applicationContent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
