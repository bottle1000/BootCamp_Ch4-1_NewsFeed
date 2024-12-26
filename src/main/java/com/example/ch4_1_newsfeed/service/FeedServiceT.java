package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.dto.feed.request.ModifyFeedRequestDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FeedResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindByUserAndFeedIdResponseDto;
import com.example.ch4_1_newsfeed.dto.user.response.FindByUserIdResponseDto;
import com.example.ch4_1_newsfeed.entity.Feed;
import com.example.ch4_1_newsfeed.repository.FeedRepository;
import com.example.ch4_1_newsfeed.repository.FeedRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceT {

    private final FeedRepositoryImpl feedRepositoryImpl;
    private final FeedRepository feedRepository;

    /**
     * 모든 피드 조회<br>
     * 현재 모든 Feed 데이터를 가져오고 있음<br>
     * 페이지네이션 적용 필요함
     */
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

    /**
     * 특정 id 뉴스피드 조회
     */
    public List<FindByUserIdResponseDto> findByUserId(Long userId) {
        return feedRepository.findById(userId).stream()
                .map(feed -> new FindByUserIdResponseDto(
                        feed.getId(),
                        feed.getUser().getName(),
                        feed.getDescription(),
                        feed.getCreatedAt(),
                        feed.getPhoto()
                )).toList();
    }

    /**
     * 특정 뉴스피드 조회
     */
    public FindByUserAndFeedIdResponseDto findByUserAndFeed(Long userId, Long feedId) {
        Feed byIdAndId = feedRepository.findByIdAndId(userId, feedId);

        return new FindByUserAndFeedIdResponseDto(
                byIdAndId.getId(),
                byIdAndId.getDescription(),
                byIdAndId.getUser(),
                byIdAndId.getPhoto(),
                byIdAndId.getCreatedAt()
        );
    }

    /**
     * 피드 수정
     */
    public FeedResponseDto updateFeed(Long feedId, ModifyFeedRequestDto dto) {
        Feed feed = feedRepositoryImpl.findByFeedId(feedId)
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND"));

        feed.updateFeed(dto.getDescription());

        return new FeedResponseDto(
                feed.getId(),
                feed.getUser().getName(),
                feed.getDescription(),
                feed.getCreatedAt()
        );

    }
}
