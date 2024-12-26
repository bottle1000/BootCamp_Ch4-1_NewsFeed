package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.SessionConst;
import com.example.ch4_1_newsfeed.dto.feed.response.FeedResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindByUserAndFeedIdResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindByUserIdResponseDto;
import com.example.ch4_1_newsfeed.entity.Feed;
import com.example.ch4_1_newsfeed.entity.Photo;
import com.example.ch4_1_newsfeed.entity.User;
import com.example.ch4_1_newsfeed.repository.FeedRepository;
import com.example.ch4_1_newsfeed.repository.PhotoRepository;
import com.example.ch4_1_newsfeed.repository.RelationshipRepository;
import com.example.ch4_1_newsfeed.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final PhotoRepository photoRepository;
    private final RelationshipRepository relationshipRepository;
    private final HttpSession session;

    /**
     * 피드 생성하기
     */
    public FeedResponseDto save(String contents, String name) {

        User findUser = userRepository.findUserByUsernameOrElseThrow(name);

        Feed feed = new Feed(contents, findUser);

        feedRepository.save(feed);


        return new FeedResponseDto(
                feed.getId(),
                findUser.getName(),
                feed.getContents(),
                feed.getCreatedAt());

    }

    /**
     * 모든 피드 조회<br>
     * 세션에서 로그인한 사용자의 ID 반환
     * Relationship 에서 사용자가 팔로우한 User 리스트 반환
     * 리스트에 포함된 유저들의 모든 게시글 반환
     * 게시글마다 사진 리스트 생성자에 주입
     * 페이지네이션 적용 필요함
     */
    public Page<FindAllFeedResponseDto> findAllFeeds(int page, int size) {
        // Feed 데이터를 모두 가져옴 (실제 서비스에서는 페이징 적용 필요)
        Long userId = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        PageRequest pageRequest = PageRequest.of(page, size);
        return feedRepository.findFeedsByUserRelationships(userId, pageRequest);
    }

    /**
     * 특정 id 뉴스피드 조회
     */
    public List<FindByUserIdResponseDto> findByUserId(Long user_id) {
        return feedRepository.findAllByUserId(user_id).stream()
                .map(feed -> new FindByUserIdResponseDto(
                        feed.getId(),
                        feed.getUser().getName(),
                        feed.getContents(),
                        feed.getCreatedAt(),
                        photoRepository.findPhotoByFeed_id(feed.getId())
                )).toList();
    }

    /**
     * 특정 뉴스피드 조회
     */
    public FindByUserAndFeedIdResponseDto findByUserAndFeed(Long user_id, Long feed_id) {
        Feed byIdAndId = feedRepository.findByIdAndId(user_id, feed_id);
        List<Photo> photos = photoRepository.findPhotoByFeed_id(feed_id);

        return new FindByUserAndFeedIdResponseDto(
                byIdAndId.getId(),
                byIdAndId.getContents(),
                byIdAndId.getUser(),
                photos,
                byIdAndId.getCreatedAt()
        );
    }

    /**
     * 피드 삭제
     */
    public void delete(Long id) {

        Feed findFeed = feedRepository.findByIdOrElseThrow(id);

        feedRepository.delete(findFeed);
    }
}