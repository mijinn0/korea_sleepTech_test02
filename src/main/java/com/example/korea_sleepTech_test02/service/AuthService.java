package com.example.korea_sleepTech_test02.service;

import com.example.korea_sleepTech_test02.dto.request.UserSignInRequestDto;
import com.example.korea_sleepTech_test02.dto.request.UserSignUpRequestDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import com.example.korea_sleepTech_test02.dto.response.UserSignInResponseDto;
import com.example.korea_sleepTech_test02.dto.response.UserSignUpResponseDto;
import jakarta.validation.Valid;

public interface AuthService {
    ResponseDto<UserSignUpResponseDto> signup(@Valid UserSignUpRequestDto dto);
    ResponseDto<UserSignInResponseDto> login(@Valid UserSignInRequestDto dto);
}
