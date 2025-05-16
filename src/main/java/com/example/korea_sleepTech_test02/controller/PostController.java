package com.example.korea_sleepTech_test02.controller;

import com.example.korea_sleepTech_test02.common.ApiMappingPattern;
import com.example.korea_sleepTech_test02.dto.request.NoticeCreateRequestDto;
import com.example.korea_sleepTech_test02.dto.request.PostCreateRequestDto;
import com.example.korea_sleepTech_test02.dto.request.PostUpdateRequestDto;
import com.example.korea_sleepTech_test02.dto.response.NoticeDetailResponseDto;
import com.example.korea_sleepTech_test02.dto.response.PostDetailResponseDto;
import com.example.korea_sleepTech_test02.dto.response.PostListResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import com.example.korea_sleepTech_test02.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.POST_API)
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> createPost(@Valid @RequestBody PostCreateRequestDto dto) {
        ResponseDto<PostDetailResponseDto> post = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    // 전체 게시글 목록 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<PostListResponseDto>>> getAllPosts() {
        ResponseDto<List<PostListResponseDto>> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 단일 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> getPostById(@PathVariable Long id) {
        ResponseDto<PostDetailResponseDto> post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    // 게시글 수정
    @PostAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> updatePost(
            @PathVariable Long id,
            @AuthenticationPrincipal String userName,
            @Valid @RequestBody PostUpdateRequestDto dto
    ) {
        ResponseDto<PostDetailResponseDto> response = postService.updatePost(id, userName, dto);
        return ResponseEntity.ok(response);
    }
}
