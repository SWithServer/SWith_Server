//작성자: 이준표
//NotificationService Test 코드
//createdAt 22.07.18
//updatedAt 22.07.20
package com.example.demo.src.service;

import com.example.demo.src.entity.Notification;
import com.example.demo.src.entity.User;
import com.example.demo.src.repository.NotificationRepository;
import com.example.demo.src.service.NotificationService;
import com.example.demo.src.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    NotificationService notificationService;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserService userService;

    @Test
    void selectNotifications() {
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
