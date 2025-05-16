package com.example.korea_sleepTech_test02.service;

import com.example.korea_sleepTech_test02.dto.request.PostCreateRequestDto;
import com.example.korea_sleepTech_test02.dto.request.PostUpdateRequestDto;
import com.example.korea_sleepTech_test02.dto.response.PostDetailResponseDto;
import com.example.korea_sleepTech_test02.dto.response.PostListResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface PostService {

    ResponseDto<PostDetailResponseDto> createPost(@Valid PostCreateRequestDto dto);
    ResponseDto<List<PostListResponseDto>> getAllPosts();
    ResponseDto<PostDetailResponseDto> getPostById(Long id);
    ResponseDto<PostDetailResponseDto> updatePost(Long id, String userName, @Valid PostUpdateRequestDto dto);
}
