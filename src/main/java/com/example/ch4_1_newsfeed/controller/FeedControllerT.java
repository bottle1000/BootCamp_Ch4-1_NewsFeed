package com.example.ch4_1_newsfeed.controller;

import com.example.ch4_1_newsfeed.dto.user.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.dto.user.response.FindByUserAndFeedIdResponseDto;
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
     * 모든 피드조회
     * 페이지네이션 구현 필요함
     */
    @GetMapping
    public ResponseEntity<List<FindAllFeedResponseDto>> findAllFeeds(@RequestParam int page, @RequestParam int size) {
        /**
         * todo : page랑 size값 받아오기만 했고 구현은 추후에 할 예정
         */

        return new ResponseEntity<>(feedServiceT.findAllFeeds(page, size), HttpStatus.OK);
    }

    /**
     * 특정 id로 뉴스피드 조회
     *
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<List> findByUserId(@PathVariable Long user_id) {

        List<FindByUserIdResponseDto> responseDtos = feedServiceT.findByUserId(user_id);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    /**
     * 특정 뉴스피드 조회
     *
     */
    @GetMapping("/{user_id}/{feed_id}")
    public ResponseEntity findByUserAndFeedId(@PathVariable Long user_id, Long feed_id) {

        FindByUserAndFeedIdResponseDto responseDtos = feedServiceT.findByUserAndFeed(user_id, feed_id);

        return new ResponseEntity(responseDtos, HttpStatus.OK);

    }
}