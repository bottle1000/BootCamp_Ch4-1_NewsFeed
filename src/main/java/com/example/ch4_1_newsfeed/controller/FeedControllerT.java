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
    public ResponseEntity<List<FindAllFeedResponseDto>> findAllFeeds(@RequestParam int page, @RequestParam int size) {
        /**
         * todo : page랑 size값 받아오기만 했고 구현은 추후에 할 예정
         */

        return new ResponseEntity<>(feedServiceT.findAllFeeds(page, size), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List> findByUserId(@PathVariable Long user_id) {

        List<FindByUserIdResponseDto> responseDtos = feedServiceT.findByUserId(user_id);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}