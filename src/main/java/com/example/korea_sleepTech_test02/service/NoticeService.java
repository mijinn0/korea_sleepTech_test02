package com.example.korea_sleepTech_test02.service;

import com.example.korea_sleepTech_test02.dto.request.NoticeCreateRequestDto;
import com.example.korea_sleepTech_test02.dto.response.NoticeDetailResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import jakarta.validation.Valid;

public interface NoticeService {
    ResponseDto<NoticeDetailResponseDto> createNotice(@Valid NoticeCreateRequestDto dto);
}
