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

    public static UpdateUserResponseDto from(User user) {
        return new UpdateUserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getProfilePicture()
        );
    }
}
