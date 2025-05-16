package com.example.korea_sleepTech_test02.service.impl;

import com.example.korea_sleepTech_test02.common.ResponseMessage;
import com.example.korea_sleepTech_test02.dto.request.NoticeCreateRequestDto;
import com.example.korea_sleepTech_test02.dto.response.NoticeDetailResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import com.example.korea_sleepTech_test02.entity.Notice;
import com.example.korea_sleepTech_test02.entity.User;
import com.example.korea_sleepTech_test02.repository.NoticeRepository;
import com.example.korea_sleepTech_test02.repository.UserRepository;
import com.example.korea_sleepTech_test02.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDto<NoticeDetailResponseDto> createNotice(NoticeCreateRequestDto dto) {
        NoticeDetailResponseDto responseDto = null;

        User user = userRepository.findByUsername(getCurrentAdminUserName())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_USER));

        Notice newNotice = Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        Notice saved = noticeRepository.save(newNotice);

        responseDto = NoticeDetailResponseDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .author(saved.getUser().getUsername())
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
