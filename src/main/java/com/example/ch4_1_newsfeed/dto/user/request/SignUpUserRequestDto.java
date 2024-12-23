package com.example.ch4_1_newsfeed.dto.user.request;

import lombok.Getter;

@Getter
public class SignUpUserRequestDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String pictureUrl;
}
