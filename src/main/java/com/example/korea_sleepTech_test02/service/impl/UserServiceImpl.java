package com.example.korea_sleepTech_test02.service.impl;

import com.example.korea_sleepTech_test02.common.ResponseMessage;
import com.example.korea_sleepTech_test02.dto.response.GetUserResponseDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import com.example.korea_sleepTech_test02.entity.User;
import com.example.korea_sleepTech_test02.repository.UserRepository;
import com.example.korea_sleepTech_test02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseDto<GetUserResponseDto> getUserInfo(String userName) {
        User user = userRepository.findByUsername(userName).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        GetUserResponseDto data = new GetUserResponseDto(user);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
