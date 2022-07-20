//작성자: 이준표
//UserService Test 코드
//createdAt 22.07.18
//updatedAt 22.07.20
package com.example.demo.src.service;

import com.example.demo.src.entity.Interest;
import com.example.demo.src.entity.User;
import com.example.demo.src.repository.UserRepository;
import com.example.demo.src.service.InterestService;
import com.example.demo.src.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InterestService interestService;


    @Test
    @DisplayName("회원가입")
    void sign_up() {
        //given
        Interest interest1 = Interest.builder()
                .interestContent("프로그래밍")
                .build();

        interestService.make_interest(interest1);

        Interest interest2 = Interest.builder()
                .interestContent("자격증")
                .build();

        interestService.make_interest(interest2);

        User user = User.builder()
                .email("new2@naver.com")
                .password("12343123215")
                .averageStar(4.5)
                .introduction("hello SW")
                .profileImgUrl("12345.com")
                .interest1(interest1)
                .interest2(interest2)
                .build();


        //when
        Long saveId = userService.join(user);

        //then
        User findUser = userService.findOne(saveId).get();
//        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }
}
