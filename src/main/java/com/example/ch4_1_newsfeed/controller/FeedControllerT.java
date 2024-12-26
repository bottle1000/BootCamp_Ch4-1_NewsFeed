package com.example.ch4_1_newsfeed.controller;

import com.example.ch4_1_newsfeed.dto.feed.request.ModifyFeedRequestDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FeedResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.dto.feed.response.FindByUserAndFeedIdResponseDto;
import com.example.ch4_1_newsfeed.dto.user.response.FindByUserIdResponseDto;
import com.example.ch4_1_newsfeed.service.FeedServiceT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedControllerT {

    private final FeedServiceT feedServiceT;

    /**
     * 모든 피드조회 <br>
     * 페이지네이션 구현 필요함 <br>
     * - todo : page랑 size값 받아오기만 했고 구현은 추후에 할 예정
     */
    @GetMapping
    public ResponseEntity<List<FindAllFeedResponseDto>> findAllFeeds(@RequestParam int page, @RequestParam int size) {

        return new ResponseEntity<>(feedServiceT.findAllFeeds(page, size), HttpStatus.OK);
    }

    /**
     * 특정 id로 뉴스피드 조회
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List> findByUserId(@PathVariable Long userId) {

        List<FindByUserIdResponseDto> responseDtos = feedServiceT.findByUserId(userId);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    /**
     * 특정 뉴스피드 조회
     */
    @GetMapping("/{userId}/{feedId}")
    public ResponseEntity findByUserAndFeedId(@PathVariable Long userId, Long feedId) {

        FindByUserAndFeedIdResponseDto responseDtos = feedServiceT.findByUserAndFeed(userId, feedId);

        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }

    /**
     * 피드 수정
     */
    @PutMapping("/{feedId}")
    public ResponseEntity<FeedResponseDto> modifyFeed(@PathVariable("feedId") Long feedId, @RequestBody ModifyFeedRequestDto dto) {
        FeedResponseDto feedResponseDto = feedServiceT.updateFeed(feedId, dto);
        return ResponseEntity.ok(feedResponseDto);
    }
}