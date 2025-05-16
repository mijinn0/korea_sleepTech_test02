package com.example.korea_sleepTech_test02.dto.response;

import com.example.korea_sleepTech_test02.entity.User;
import lombok.Getter;

@Getter
public class GetUserResponseDto {
    private Long id;
    private String username;
    private String role;

    public GetUserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
