package com.example.demo.src.repository;

import com.example.demo.src.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query("select n from Notification n where n.user.userIdx = ?1")
    List<Notification> findAllByUser(Long userIdx);
}
