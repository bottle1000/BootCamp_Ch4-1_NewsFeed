package com.example.ch4_1_newsfeed.dto.user.response;

import com.example.ch4_1_newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserResponseDto {

    private Long id;
    private String email;
    private String name;
    private String pictureUrl;

    // 정적 팩토리 메서드
    public static UpdateUserResponseDto from(User user) {
        return new UpdateUserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getProfilePicture()
        );
    }
}
