package com.example.korea_sleepTech_test02.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NoticeDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
}
