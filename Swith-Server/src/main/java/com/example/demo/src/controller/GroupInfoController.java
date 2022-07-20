package com.example.demo.src.controller;

import com.example.demo.src.entity.GroupInfo;
import com.example.demo.src.service.GroupInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/study")
public class GroupInfoController {

    private final GroupInfoService groupInfoService;

    public GroupInfoController(GroupInfoService groupInfoService) {
        this.groupInfoService = groupInfoService;
    }

    @GetMapping("/{id}")
    public List<GroupInfo> load_studies(@PathVariable("id") Long userIdx){
        //DTO로 필요한 정보만 보내주기
        return groupInfoService.loadStudies(userIdx);
    }
}
