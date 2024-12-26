package com.example.ch4_1_newsfeed.controller;

import com.example.ch4_1_newsfeed.common.SessionConst;
import com.example.ch4_1_newsfeed.model.dto.feed.request.FeedRequestDto;
import com.example.ch4_1_newsfeed.model.dto.feed.request.ModifyFeedRequestDto;
import com.example.ch4_1_newsfeed.model.dto.feed.response.FeedResponseDto;
import com.example.ch4_1_newsfeed.model.dto.feed.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.model.dto.feed.response.FindByUserAndFeedIdResponseDto;
import com.example.ch4_1_newsfeed.model.dto.feed.response.FindByUserIdResponseDto;
import com.example.ch4_1_newsfeed.model.dto.user.response.ProfileUserResponseDto;
import com.example.ch4_1_newsfeed.service.FeedService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedController {

    private final FeedService feedService;

    /**
     * 피드 생성
     */
    @PostMapping
    public ResponseEntity<FeedResponseDto> save(@RequestBody FeedRequestDto requestDto) {

        FeedResponseDto feedResponseDto =
                feedService.save(
                        requestDto.getContents(),
                        requestDto.getName()
                );

        return new ResponseEntity<>(feedResponseDto, HttpStatus.CREATED);

    }

    /**
     * 모든 피드조회 <br>
     * 페이지네이션 구현 필요함 <br>
     * todo : page랑 size값 받아오기만 했고 구현은 추후에 할 예정
     */
    @GetMapping
    public ResponseEntity<List<FindAllFeedResponseDto>> findAllFeeds(
        @Valid @NotNull(message = "page가 포함되어야 합니다.") @PositiveOrZero(message = "page는 양의 정수 또는 0여어야 합니다.") @RequestParam int page,
        @Valid @Positive(message = "size는 양의 정수여야 합니다.") @RequestParam int size
    ) {

        List<FindAllFeedResponseDto> allFeeds = feedService.findAllFeeds(page,size);
        return new ResponseEntity<>(allFeeds, HttpStatus.OK);
    }

    /**
     * 특정 userId로 사용자의 피드를 조회<br>
     * 1페이지 10개 피드 출력
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<List> findByUserId(
        @Valid @NotNull @Positive(message = "user_id는 양의 정수여야 합니다.") @PathVariable Long userId,
        @Valid @NotNull(message = "page가 포함되어야 합니다.") @PositiveOrZero(message = "page는 양의 정수 또는 0이여야 합니다.") @RequestParam int page,
        @Valid @Positive(message = "size는 양의 정수여야 합니다.") @RequestParam(defaultValue = "10") int size
    ) {

        List<FindByUserIdResponseDto> responseDtos = feedService.findByUserId(userId, page, size);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    /**
     * 본인 뉴스피드 조회 <br>
     * 1페이지 10개 피드 출력
     * @param session
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity<ProfileUserResponseDto> getMyProfile(
            HttpSession session,
            @Valid
            @NotNull(message = "page가 포함되어야 합니다.")
            @PositiveOrZero(message = "page는 양의 정수 또는 0이여야 합니다.")
            @RequestParam int page,
            @Valid
            @Positive(message = "size는 양의 정수여야 합니다.")
            @RequestParam(defaultValue = "10") int size) {
        Long userId = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        ProfileUserResponseDto myProfile = feedService.getMyProfile(userId, page, size);

        return new ResponseEntity<>(myProfile, HttpStatus.OK);
    }

    /**
     * 특정 user의 하나의 피드 조회 <br>
     * @param userId
     * @param feedId
     * @return
     */
    @GetMapping("/{user_id}/{feed_id}")
    public ResponseEntity findByUserAndFeedId(@PathVariable Long userId, Long feedId) {

        FindByUserAndFeedIdResponseDto responseDtos = feedService.findByUserAndFeed(userId, feedId);

        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }

    /**
     * 본인 피드 수정
     * @param feedId
     * @param dto
     * @return
     */
    @PutMapping("/{feed_id}")
    public ResponseEntity<FeedResponseDto> modifyFeed(@PathVariable("feedId") Long feedId, @RequestBody ModifyFeedRequestDto dto) {
        FeedResponseDto feedResponseDto = feedService.updateFeed(feedId, dto);
        return ResponseEntity.ok(feedResponseDto);
    }

    /**
     * 피드 삭제
     * @param feedId
     * @return
     */
    @DeleteMapping("/{feed_id}")
    public ResponseEntity<Void> delete(@PathVariable Long feedId) {

        feedService.delete(feedId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}