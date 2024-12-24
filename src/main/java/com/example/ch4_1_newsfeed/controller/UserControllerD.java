package com.example.ch4_1_newsfeed.controller;

import com.example.ch4_1_newsfeed.dto.user.response.RelationshipResponseDto;
import com.example.ch4_1_newsfeed.service.UserServiceD;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usersD")
public class UserControllerD {

    private final UserServiceD userService;

    @PostMapping("/{id}")
    public ResponseEntity<RelationshipResponseDto> follow(
        @Valid
        @Positive(message = "id는 양의 정수여야 합니다.")
        @PathVariable Long id, HttpSession session
    ) {

        RelationshipResponseDto following = userService.follow(id, session);

        return new ResponseEntity<>(following, HttpStatus.OK);
    }
}
