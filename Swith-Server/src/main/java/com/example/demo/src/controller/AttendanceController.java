package com.example.demo.src.controller;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.dto.response.GetGroupAttendanceRes;
import com.example.demo.src.repository.AttendanceRepository;
import com.example.demo.src.service.AttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"Swith Attendance API"})
@RequestMapping("/groupinfo/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @ApiOperation("스터디탭 - 출석 현황 조회 - P9")
    @GetMapping()
    public BaseResponse<GetGroupAttendanceRes> getGroupAttendance(@RequestParam Long groupIdx){
        try{
            GetGroupAttendanceRes getGroupAttendanceRes = attendanceService.getGroupAttendance(groupIdx);
            return new BaseResponse<>(getGroupAttendanceRes);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

}