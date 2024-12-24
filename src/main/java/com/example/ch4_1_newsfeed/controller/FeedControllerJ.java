package com.example.ch4_1_newsfeed.controller;

import com.example.ch4_1_newsfeed.dto.feed.FeedRequestDto;
import com.example.ch4_1_newsfeed.dto.feed.FeedResponseDto;
import com.example.ch4_1_newsfeed.service.FeedServiceJ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedControllerJ {

    private final FeedServiceJ feedServicej;

    @PostMapping
    public ResponseEntity<FeedResponseDto> save(@RequestBody FeedRequestDto requestDto) {

        FeedResponseDto feedResponseDto =
                feedServicej.save(
                        requestDto.getContents(),
                        requestDto.getName()
                );

        return new ResponseEntity<>(feedResponseDto, HttpStatus.CREATED);

    }

}


