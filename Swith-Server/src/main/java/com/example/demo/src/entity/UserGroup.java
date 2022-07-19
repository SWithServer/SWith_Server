//작성자: 이준표
//UserGroup 엔티티 JPA 객체 매핑
//createdAt 22.07.15
//updaredAt 22.07.15
package com.example.demo.src.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "USER_GROUP")
public class UserGroup {
    @Id
    @Column(name = "USER_GROUP_IDX")
    private long userGroupIdx;

    //FK 2개
    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    @ManyToOne
    @JoinColumn(name = "GROUP_INFO_IDX")
    private GroupInfo groupInfo;

    private Byte status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
