package com.example.ch4_1_newsfeed.model.dto.user.response;

import com.example.ch4_1_newsfeed.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelationshipResponseDto {

    private String following;
    private String followed;
    private String message;

    // 정적 팩토리 메서드
    public static RelationshipResponseDto of(User following, User followed) {
        return new RelationshipResponseDto(
                following.getName(),
                followed.getName(),
                "you followed " + followed.getName()
        );
    }
}
