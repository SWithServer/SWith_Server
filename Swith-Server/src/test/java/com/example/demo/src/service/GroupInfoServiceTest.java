//작성자: 이준표
//GroupInfoService Test 코드
//createdAt 22.07.18
//updatedAt 22.07.20
package com.example.demo.src.service;

import com.example.demo.src.entity.GroupInfo;
import com.example.demo.src.entity.Register;
import com.example.demo.src.entity.User;
import com.example.demo.src.repository.GroupInfoRepository;
import com.example.demo.src.repository.RegisterRepository;
import com.example.demo.src.repository.UserRepository;
import com.example.demo.src.service.GroupInfoService;
import com.example.demo.src.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupInfoServiceTest {
    @Autowired
    GroupInfoService groupInfoService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    GroupInfoRepository groupInfoRepository;


    @Test
    @DisplayName("유저가 가입한 스터디의 인덱스를 불러온다.")
    void load_studies() {
        //given
        List<GroupInfo> groupInfos = groupInfoService.loadStudies(1L);

        for (GroupInfo groupInfo : groupInfos) {
            System.out.println("groupInfos = " + groupInfo.getGroupInfoIdx());
        }

        //when
//        groupInfoService.
        //then
    }
}