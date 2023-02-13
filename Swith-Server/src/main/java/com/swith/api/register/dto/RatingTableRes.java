package com.swith.api.register.dto;


import com.swith.domain.user.entity.User;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class RatingTableRes {

    private Long userIdx;
    private String nickname;

    public static RatingTableRes from(User user){
        return RatingTableRes.builder()
                .userIdx(user.getUserIdx())
                .nickname(user.getNickname())
                .build();
    }


}
