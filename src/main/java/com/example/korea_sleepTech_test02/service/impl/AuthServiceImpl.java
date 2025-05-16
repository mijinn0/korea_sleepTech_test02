package com.example.korea_sleepTech_test02.service.impl;

import com.example.korea_sleepTech_test02.common.ResponseMessage;
import com.example.korea_sleepTech_test02.dto.request.UserSignInRequestDto;
import com.example.korea_sleepTech_test02.dto.request.UserSignUpRequestDto;
import com.example.korea_sleepTech_test02.dto.response.ResponseDto;
import com.example.korea_sleepTech_test02.dto.response.UserSignInResponseDto;
import com.example.korea_sleepTech_test02.dto.response.UserSignUpResponseDto;
import com.example.korea_sleepTech_test02.entity.User;
import com.example.korea_sleepTech_test02.provider.JwtProvider;
import com.example.korea_sleepTech_test02.repository.UserRepository;
import com.example.korea_sleepTech_test02.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<UserSignUpResponseDto> signup(UserSignUpRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String role = dto.getRole();

        UserSignUpResponseDto data = null;
        User user = null;

        // 패스워드 일치 여부
        if (!password.equals(confirmPassword)) {
            // 일치하지 않은 경우
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        // 중복되는 이메일 검증
        if (userRepository.existsByUsername(username)) {
            // 중복 되는 경우 (사용할 수 X)
            return ResponseDto.setFailed(ResponseMessage.EXIST_DATA);
        }

        // 패스워드 암호화
        String encodePassword = bCryptPasswordEncoder.encode(password);

        user = User.builder()
                .username(username)
                .password(encodePassword)
                .role(role)
                .build();

        userRepository.save(user);

        data = new UserSignUpResponseDto(user);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        UserSignInResponseDto data = null;
        User user = null;

        int exprTime = jwtProvider.getExpiration();

        user = userRepository.findByUsername(username)
                .orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            // .matches(평문 비밀번호, 암호화된 비밀번호)
            // : 평문 비밀번호(실제 비밀번호)와 암호화된 비밀번호를 비교하여 일치 여부 반환(boolean)
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        String token = jwtProvider.generateJwtToken(username, user.getRole());

        data = new UserSignInResponseDto(token, user, exprTime);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
