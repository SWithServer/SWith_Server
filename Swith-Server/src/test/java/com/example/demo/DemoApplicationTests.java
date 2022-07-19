package com.example.demo;

import com.example.demo.src.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void 회원가입() {
        //given
        User user = User.builder()
                .email("new1@naver.com")
                .averageStar(4.5)
                .introduction("hello SW")
                .password("12345")
                .profileImgUrl("12345.com")
                .status(0)
                .build();

        //when
//        Long saveId = userService.join(user);

        //then
//        User findUser = userService.findOne(saveId).get();
//        findUser.setEmail("WEQWE@naver.com");
    }

}
