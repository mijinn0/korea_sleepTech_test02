package com.example.korea_sleepTech_test02.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDto {
    @NotNull
    private String username;

    @NotNull
    private String password; // 비밀번호

    @NotNull
    private String confirmPassword; // 비밀번호 확인

    @NotNull
    private String role;
}