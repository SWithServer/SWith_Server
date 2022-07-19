package com.example.demo.service;

import com.example.demo.src.entity.Notification;
import com.example.demo.src.entity.User;
import com.example.demo.src.repository.NotificationRepository;
import com.example.demo.src.service.NotificationService;
import com.example.demo.src.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NotificationServiceTest {

    @Autowired
    NotificationService notificationService;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserService userService;

    @Test
    public void selectNotifications() {
        //given
        Long userIdx = 1L;
        User user = userService.findOne(userIdx).get();
        Notification notification = Notification.builder()
                .user(user)
                .notificationContent("test1")
                .notificationIdx(2L)
                .build();
        notificationService.makeNotification(notification);

        //when
        List<Notification> list = notificationService.selectList(userIdx);

        //then
        for (int i = 0 ; i < list.size() ; i++){
            System.out.println("list = " + list.get(i).getNotificationContent());
        }
    }
}
