package com.example.korea_sleepTech_test02.controller;

import com.example.korea_sleepTech_test02.common.ApiMappingPattern;
import com.example.korea_sleepTech_test02.dto.request.NoticeCreateRequestDto;
import com.example.korea_sleepTech_test02.dto.response.NoticeDetailResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import com.example.korea_sleepTech_test02.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.NOTICE_API)
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseDto<NoticeDetailResponseDto>> createNotice(@Valid @RequestBody NoticeCreateRequestDto dto) {
        ResponseDto<NoticeDetailResponseDto> notice = noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(notice);
    }
}
