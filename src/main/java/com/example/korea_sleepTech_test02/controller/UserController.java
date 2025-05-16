package com.example.korea_sleepTech_test02.controller;

import com.example.korea_sleepTech_test02.common.ApiMappingPattern;
import com.example.korea_sleepTech_test02.dto.response.GetUserResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import com.example.korea_sleepTech_test02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.USER_API)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // === UserController mapping pattern === //
    private static final String GET_USER_INFO = "/me";

    // 회원 정보 조회
    @GetMapping(GET_USER_INFO)
    public ResponseEntity<ResponseDto<GetUserResponseDto>> getUserInfo(
            @AuthenticationPrincipal String userName
    ) {
        ResponseDto<GetUserResponseDto> response = userService.getUserInfo(userName);
        return ResponseEntity.ok(response);
    }
}
