package com.example.ch4_1_newsfeed.dto.user.response;

import com.example.ch4_1_newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SignUpUserResponseDto {

    private Long id;
    private String email;
    private String name;
    private LocalDateTime createdAt;

    public static SignUpUserResponseDto from (User user) {
        return new SignUpUserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedDate()
        );
    }
}
