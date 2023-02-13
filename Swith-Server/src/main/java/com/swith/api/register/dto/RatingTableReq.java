package com.swith.api.register.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RatingTableReq {

    private Long groupIdx;
    private Long userIdx;
}
