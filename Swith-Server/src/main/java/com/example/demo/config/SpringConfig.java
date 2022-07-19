package com.example.demo.config;

import com.example.demo.src.repository.InterestRepository;
import com.example.demo.src.repository.NotificationRepository;
import com.example.demo.src.repository.UserRepository;
import com.example.demo.src.service.InterestService;
import com.example.demo.src.service.NotificationService;
import com.example.demo.src.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final UserRepository userRepository;
    private final InterestRepository interestRepository;
    private final NotificationRepository notificationRepository;

    public SpringConfig(UserRepository userRepository, InterestRepository interestRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
        this.notificationRepository = notificationRepository;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository);}

    @Bean
    public InterestService interestService() {
        return new InterestService(interestRepository);
    }

    @Bean
    public NotificationService notificationService(){
        return new NotificationService(notificationRepository);
    }
}
