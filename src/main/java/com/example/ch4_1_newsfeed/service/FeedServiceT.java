package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.dto.user.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.dto.user.response.FindByUserAndFeedIdResponseDto;
import com.example.ch4_1_newsfeed.dto.user.response.FindByUserIdResponseDto;
import com.example.ch4_1_newsfeed.entity.Feed;
import com.example.ch4_1_newsfeed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceT {

    private final FeedRepository feedRepository;

    public List<FindAllFeedResponseDto> findAllFeeds(int page, int size) {
        // Feed 데이터를 모두 가져옴 (실제 서비스에서는 페이징 적용 필요)
        List<Feed> feeds = feedRepository.findAll();

        return feeds.stream()
                .map(feed -> new FindAllFeedResponseDto(
                        feed.getId(),
                        feed.getUser().getId(),
                        feed.getDescription(),
                        feed.getCreatedAt(),
                        feed.getPhoto() // Feed와 연결된 Photo 리스트
                )).toList();
    }

    public List<FindByUserIdResponseDto> findByUserId(Long user_id) {
        return feedRepository.findById(user_id).stream()
                .map(feed -> new FindByUserIdResponseDto(
                        feed.getId(),
                        feed.getUser().getName(),
                        feed.getDescription(),
                        feed.getCreatedAt(),
                        feed.getPhoto()
                )).toList();
    }

    public FindByUserAndFeedIdResponseDto findByUserAndFeed(Long user_id, Long feed_id) {
        Feed byIdAndId = feedRepository.findByIdAndId(user_id, feed_id);

        return new FindByUserAndFeedIdResponseDto(
                byIdAndId.getId(),
                byIdAndId.getDescription(),
                byIdAndId.getUser(),
                byIdAndId.getPhoto(),
                byIdAndId.getCreatedAt()
        );
    }
}
