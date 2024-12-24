package com.example.ch4_1_newsfeed.controller;

import com.example.ch4_1_newsfeed.dto.user.response.FindAllFeedResponseDto;
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

    @GetMapping
    public ResponseEntity<List<FindAllFeedResponseDto>> findAllFeeds(
        @Valid @NotNull(message = "page가 포함되어야 합니다.") @Positive(message = "page는 양의 정수여야 합니다.") @RequestParam int page,
        @Valid @Positive(message = "size는 양의 정수여야 합니다.") @RequestParam int size
    ) {
        /**
         * todo : page랑 size값 받아오기만 했고 구현은 추후에 할 예정
         */

        return new ResponseEntity<>(feedServiceT.findAllFeeds(page, size), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List> findByUserId(
        @Valid @NotNull @Positive(message = "user_id는 양의 정수여야 합니다.") @PathVariable Long user_id
    ) {

        List<FindByUserIdResponseDto> responseDtos = feedServiceT.findByUserId(user_id);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}