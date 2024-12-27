package com.example.ch4_1_newsfeed.model.dto.feed.response;

import com.example.ch4_1_newsfeed.model.entity.Feed;
import com.example.ch4_1_newsfeed.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FindAllFeedsByUserIdDto {

    private Long userId;
    private List<Feed> feedList;

    public static FindAllFeedsByUserIdDto from(User user, List<Feed> feedList) {
       return new FindAllFeedsByUserIdDto(
                user.getId(),
                feedList
       );
    }
}
