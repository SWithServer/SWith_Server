package com.example.demo.src.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetGroupInfoSearchReq {

    private String title; //스터디 그룹의 제목

    private Long regionIdx; //이거 다중선택하면 list로 보내나?

    private Integer interest1;

    private Integer interest2;

    private Long groupIdx;

    private Integer sortCond; //마감일이 최초값..

    //private LocalDateTime



}