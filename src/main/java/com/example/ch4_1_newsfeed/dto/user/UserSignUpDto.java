package com.example.ch4_1_newsfeed.dto.user;

import com.example.ch4_1_newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSignUpDto {

    private Long id;
    private String email;
    private String name;
    private LocalDateTime createdAt;

    private UserSignUpDto(Long id, String email, String name, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static UserSignUpDto from (User user) {
        return new UserSignUpDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedDate()
        );
    }
}
