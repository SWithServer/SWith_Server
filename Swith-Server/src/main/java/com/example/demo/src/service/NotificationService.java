package com.example.demo.src.service;

import com.example.demo.src.entity.Notification;
import com.example.demo.src.repository.NotificationRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> selectList(Long userIdx){
        return notificationRepository.findAllByUser(userIdx);
    }

    public void makeNotification(Notification notification){
        notificationRepository.save(notification);
    }

}
