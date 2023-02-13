package com.swith.api.register.controller;

import com.swith.api.register.dto.RatingTableReq;
import com.swith.api.register.dto.RatingTableRes;
import com.swith.api.register.service.RegisterApiService;
import com.swith.domain.groupinfo.service.GroupInfoService;
import com.swith.domain.register.service.RegisterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
@Api(tags = {"Swith Register API"})
public class RegisterController {

    private RegisterApiService registerApiService;
    private GroupInfoService groupInfoService;


    @Autowired
    public RegisterController(RegisterApiService registerApiService, GroupInfoService groupInfoService) {
        this.registerApiService = registerApiService;
        this.groupInfoService = groupInfoService;
    }




    @ResponseBody
    @PostMapping("/ratingtable") //보안때문에 Post로 작성
    public ResponseEntity<List<RatingTableRes>> getRatingTable(@RequestBody RatingTableReq ratingTableReq){

        //상태가 종료상태인지..
        groupInfoService.statusEndGroup(ratingTableReq.getGroupIdx());

        return ResponseEntity.ok(registerApiService.getRatingTable(ratingTableReq));
    }
}
