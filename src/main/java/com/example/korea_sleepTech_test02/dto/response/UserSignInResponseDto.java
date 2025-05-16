package com.example.korea_sleepTech_test02.dto.response;

import com.example.korea_sleepTech_test02.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {
    private String token; // JWT 토큰
    private User user;
    private int exprTime; // expire + time: (토큰) 만료 시간
}