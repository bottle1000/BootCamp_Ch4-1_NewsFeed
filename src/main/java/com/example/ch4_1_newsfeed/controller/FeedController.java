package com.example.ch4_1_newsfeed.controller;

import com.example.ch4_1_newsfeed.dto.feed.request.FeedRequestDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FeedResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindByUserAndFeedIdResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindByUserIdResponseDto;
import com.example.ch4_1_newsfeed.service.FeedService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedController {

    private final FeedService feedServiceT;

    /**
     * 피드 생성
     */
    @PostMapping
    public ResponseEntity<FeedResponseDto> save(@RequestBody FeedRequestDto requestDto) {

        FeedResponseDto feedResponseDto =
                feedServiceT.save(
                        requestDto.getContents(),
                        requestDto.getName()
                );

        return new ResponseEntity<>(feedResponseDto, HttpStatus.CREATED);

    }

    /**
     * 모든 피드조회 <br>
     * 페이지네이션 구현 필요함 <br>
     * - todo : page랑 size값 받아오기만 했고 구현은 추후에 할 예정
     */
    @GetMapping
    public ResponseEntity<Page<FindAllFeedResponseDto>> findAllFeeds(
        @Valid @NotNull(message = "page가 포함되어야 합니다.") @Positive(message = "page는 양의 정수여야 합니다.") @RequestParam int page,
        @Valid @Positive(message = "size는 양의 정수여야 합니다.") @RequestParam int size
    ) {

        Page<FindAllFeedResponseDto> allFeeds = feedServiceT.findAllFeeds(page,size);
        return new ResponseEntity<>(allFeeds, HttpStatus.OK);
    }

    /**
     * 특정 id로 뉴스피드 조회
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<List> findByUserId(
        @Valid @NotNull @Positive(message = "user_id는 양의 정수여야 합니다.") @PathVariable Long user_id
    ) {

        List<FindByUserIdResponseDto> responseDtos = feedServiceT.findByUserId(user_id);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    /**
     * 특정 뉴스피드 조회
     */
    @GetMapping("/{user_id}/{feed_id}")
    public ResponseEntity findByUserAndFeedId(@PathVariable Long user_id, Long feed_id) {

        FindByUserAndFeedIdResponseDto responseDtos = feedServiceT.findByUserAndFeed(user_id, feed_id);

        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }

    /**
     * 피드 수정
     */
    @PutMapping("/{feed_id}")
    public ResponseEntity<FeedResponseDto> modifyFeed(@PathVariable("feed_id") Long feed_id, @RequestBody ModifyFeedRequestDto dto) {
        FeedResponseDto feedResponseDto = feedService.updateFeed(feed_id, dto);
        return ResponseEntity.ok(feedResponseDto);
    }

    /**
     * 피드 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        feedServiceT.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}