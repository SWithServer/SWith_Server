package com.example.demo.src.controller;


import com.example.demo.config.BaseResponse;
import com.example.demo.src.dto.request.PostRatingReq;
import com.example.demo.src.dto.request.PostRatingStarReq;
import com.example.demo.src.dto.response.PostRatingRes;
import com.example.demo.src.service.GroupInfoService;
import com.example.demo.src.service.RatingService;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@Api(tags = {"Swith Rating API"})
public class RatingController {

    private final GroupInfoService groupInfoService;
    private final RatingService ratingService;

    @Autowired
    public RatingController(GroupInfoService groupInfoService, RatingService ratingService) {
        this.groupInfoService = groupInfoService;
        this.ratingService = ratingService;
    }

    @ApiOperation("List Rating")
    @ResponseBody
    @PostMapping("/list/{groupIdx}")
    public BaseResponse<List<PostRatingRes>> GetListRatingUserSinceGroupEnd(@PathVariable Long groupIdx, @RequestBody PostRatingReq postRatingReq){

        //그룹 끝났어?₩₩₩₩₩₩₩₩₩₩₩
        Integer status = groupInfoService.statusOfGroupInfo(groupIdx);
        if(status != 2){//종료된 그룹이 아니다.

        }
        //그룹 회원이야? + 그룹 list 던짐
        List<PostRatingRes> answer = ratingService.RatingUserSinceGroupEnd(groupIdx, postRatingReq);
        return new BaseResponse<>(answer);

    }

    @ResponseBody
    @PostMapping("/evaluate/{groupIdx}")
    public BaseResponse<String> RatingStart(@PathVariable Long groupIdx, @RequestBody PostRatingStarReq postRatingStarReq){
        System.out.println(groupIdx);
        System.out.println(postRatingStarReq.getRaterIdx());
        System.out.println(postRatingStarReq.getStar());

        //그룹 끝났어?
        Integer status = groupInfoService.statusOfGroupInfo(groupIdx);
        if(status != 2){//종료된 그룹이 아니다.

        }
        String check = ratingService.RatingStart(postRatingStarReq);
        if(!check.equals("평가완료")) return new BaseResponse<>("평가 실패");

        return new BaseResponse<>(check);
    }

}
