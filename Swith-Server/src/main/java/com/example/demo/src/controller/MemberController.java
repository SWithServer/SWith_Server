package com.example.demo.src.controller;

import com.example.demo.src.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MemberController {
    private final UserService userService;

    public MemberController(UserService userService) {
        this.userService = userService;
    }

}
