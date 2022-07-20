package com.example.demo.config;

import com.example.demo.src.repository.*;
import com.example.demo.src.service.GroupInfoService;
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
    private final GroupInfoRepository groupInfoRepository;
    private final RegisterRepository registerRepository;

    public SpringConfig(UserRepository userRepository, InterestRepository interestRepository, NotificationRepository notificationRepository, GroupInfoRepository groupInfoRepository, RegisterRepository registerRepository) {
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
        this.notificationRepository = notificationRepository;
        this.groupInfoRepository = groupInfoRepository;
        this.registerRepository = registerRepository;
    }

    @Bean
    public UserService userService() {return new UserService(userRepository, registerRepository);}

    @Bean
    public InterestService interestService() {
        return new InterestService(interestRepository);
    }

    @Bean
    public NotificationService notificationService(){
        return new NotificationService(notificationRepository);
    }

    @Bean
    public GroupInfoService groupInfoService() { return new GroupInfoService(groupInfoRepository, registerRepository);}
}
