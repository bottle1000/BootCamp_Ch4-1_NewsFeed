package com.example.ch4_1_newsfeed.model.dto.user.response;

import com.example.ch4_1_newsfeed.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String email;
    private String name;

    /**
     * 정적 팩토리 메서드
     */
    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}