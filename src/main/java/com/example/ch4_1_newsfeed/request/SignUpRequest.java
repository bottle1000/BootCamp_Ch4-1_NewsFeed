package com.example.ch4_1_newsfeed.request;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String pictureUrl;
}
