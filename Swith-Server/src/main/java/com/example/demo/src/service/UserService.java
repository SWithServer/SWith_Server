package com.example.demo.src.service;


import com.example.demo.src.entity.GroupInfo;
import com.example.demo.src.entity.Register;
import com.example.demo.src.entity.User;
import com.example.demo.src.repository.GroupInfoRepository;
import com.example.demo.src.repository.RegisterRepository;
import com.example.demo.src.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RegisterRepository registerRepository;

    public UserService(UserRepository userRepository, RegisterRepository registerRepository) {
        this.userRepository = userRepository;
        this.registerRepository = registerRepository;
    }

    public Long join(User user) {
        userRepository.save(user);
        return user.getUserIdx();
    }

    public Optional<User> findOne(Long userIdx) {
        return userRepository.findById(userIdx);
    }

    public List<GroupInfo> findRegistered (Long userIdx){
        User user = userRepository.findById(userIdx).get();

        List<Register> registers = user.getRegisters();

        List<GroupInfo> groupInfos = new ArrayList<>();

        for (Register register : registers){
            groupInfos.add(register.getGroupInfo());
        }
        return groupInfos;
    }

}
