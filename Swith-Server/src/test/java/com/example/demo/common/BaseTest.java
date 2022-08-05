package com.example.demo.common;

import com.example.demo.src.entity.Interest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class BaseTest {
//    @Autowired
//    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;


    public String email = "test1@naver.com";
    public String password = "password";
    public String nickname = "nick";
    public Interest interest1 = Interest.builder().interestIdx(0).interestContent("test1").build();
    public Interest interest2 = Interest.builder().interestIdx(2).interestContent("test2").build();
//    public Interest interest1 = new Interest(1, "test");
//    public Interest interest2 = new Interest(2, "test2");
    public String introduction = "안녕하세요, 신입 회원입니다!";
}