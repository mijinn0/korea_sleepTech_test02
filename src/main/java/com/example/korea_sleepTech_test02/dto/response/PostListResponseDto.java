package com.example.korea_sleepTech_test02.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;
}