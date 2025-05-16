package com.example.korea_sleepTech_test02.dto.response;

import com.example.korea_sleepTech_test02.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpResponseDto {
    User user;
}
