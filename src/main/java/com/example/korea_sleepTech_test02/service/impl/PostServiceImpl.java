package com.example.korea_sleepTech_test02.service.impl;

import com.example.korea_sleepTech_test02.common.ResponseMessage;
import com.example.korea_sleepTech_test02.dto.request.PostCreateRequestDto;
import com.example.korea_sleepTech_test02.dto.request.PostUpdateRequestDto;
import com.example.korea_sleepTech_test02.dto.response.PostDetailResponseDto;
import com.example.korea_sleepTech_test02.dto.response.PostListResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import com.example.korea_sleepTech_test02.entity.Post;
import com.example.korea_sleepTech_test02.entity.User;
import com.example.korea_sleepTech_test02.repository.PostRepository;
import com.example.korea_sleepTech_test02.repository.UserRepository;
import com.example.korea_sleepTech_test02.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDto<PostDetailResponseDto> createPost(PostCreateRequestDto dto) {
        PostDetailResponseDto responseDto = null;

        User user = userRepository.findByUsername(getCurrentAdminUserName())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_USER));

        boolean isUser = user.getRole().equals("USER");

        if (!isUser) {
            throw new IllegalStateException("해당 사용자는 USER 권한이 없습니다.");
        }

        Post newPost = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        Post saved = postRepository.save(newPost);

        responseDto = PostDetailResponseDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .author(saved.getUser().getUsername())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostListResponseDto>> getAllPosts() {
        List<PostListResponseDto> responseDtos = null;

        List<Post> posts = postRepository.findAll();

        responseDtos = posts.stream()
                .map(post -> PostListResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .author(post.getUser().getUsername())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<PostDetailResponseDto> getPostById(Long id) {
        PostDetailResponseDto responseDto = null;

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        responseDto = PostDetailResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUsername())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<PostDetailResponseDto> updatePost(Long id, String userName, PostUpdateRequestDto dto) {
        PostDetailResponseDto responseDto = null;

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        if (!post.getUser().getUsername().equals(userName)) {
            throw new IllegalStateException("해당 사용자가 작성한 글이 아닙니다.");
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        Post updatedPost = postRepository.save(post);

        responseDto = PostDetailResponseDto.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .author(updatedPost.getUser().getUsername())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    // 현재 로그인한 사용자의 이메일 또는 사용자명을 가져오는 함수
    private String getCurrentAdminUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // auth.getName()
        // : 토큰안에 저장된(로그인한 사용자의) username 또는 email을 반환
        return auth != null ? auth.getName() : "unknown";
    }
}
