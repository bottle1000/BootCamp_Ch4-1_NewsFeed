package com.example.ch4_1_newsfeed.dto.user.response;

import com.example.ch4_1_newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelationshipResponseDto {

    private User following;
    private User followed;
    private String message;

    // 정적 팩토리 메서드
    public static RelationshipResponseDto of(User following, User followed) {
        return new RelationshipResponseDto(
                following,
                followed,
                "you followed " + followed.getName()
        );
    }
}
