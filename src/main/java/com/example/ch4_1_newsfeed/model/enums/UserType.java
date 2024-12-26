package com.example.ch4_1_newsfeed.model.enums;

public enum UserType {
    GUEST, MEMBER, ADMIN;


    public boolean isAdmin() {
        return this == ADMIN;
    }
}