package com.example.ch4_1_newsfeed.dto.user.response;

import com.example.ch4_1_newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String email;
    private String name;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }

}
