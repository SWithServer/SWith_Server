package com.example.demo.src.service;


import com.example.demo.src.entity.User;
import com.example.demo.src.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user) {
        userRepository.save(user);
        return user.getUserIdx();
    }

    public Optional<User> findOne(Long userIdx) {
        return userRepository.findById(userIdx);
    }

}
