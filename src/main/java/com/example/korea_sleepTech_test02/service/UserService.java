package com.example.korea_sleepTech_test02.service;

import com.example.korea_sleepTech_test02.dto.response.GetUserResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;

public interface UserService {
    ResponseDto<GetUserResponseDto> getUserInfo(String userName);
}
