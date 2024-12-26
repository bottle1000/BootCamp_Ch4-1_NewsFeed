package com.example.ch4_1_newsfeed.dto.user.response;

import com.example.ch4_1_newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class RelationshipResponseDto {

    private User following;
    private User followed;
    private String message;
}
