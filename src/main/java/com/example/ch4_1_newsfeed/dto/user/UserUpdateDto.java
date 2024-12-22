package com.example.ch4_1_newsfeed.dto.user;

import com.example.ch4_1_newsfeed.entity.User;
import lombok.Getter;

@Getter
public class UserUpdateDto {

    private Long id;
    private String email;
    private String name;
    private String pictureUrl;

    private UserUpdateDto(Long id, String email, String name, String pictureUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static UserUpdateDto from(User user) {
        return new UserUpdateDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getProfilePicture()
        );
    }
}
