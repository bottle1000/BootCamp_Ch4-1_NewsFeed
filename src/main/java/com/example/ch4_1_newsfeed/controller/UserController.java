package com.example.ch4_1_newsfeed.controller;


import com.example.ch4_1_newsfeed.dto.user.ProfileDto;
import com.example.ch4_1_newsfeed.dto.user.UserDto;
import com.example.ch4_1_newsfeed.dto.user.UserSignUpDto;
import com.example.ch4_1_newsfeed.dto.user.UserUpdateDto;
import com.example.ch4_1_newsfeed.request.LoginRequest;
import com.example.ch4_1_newsfeed.request.SignUpRequest;
import com.example.ch4_1_newsfeed.request.UpdatePasswordRequest;
import com.example.ch4_1_newsfeed.request.UpdateUserRequest;
import com.example.ch4_1_newsfeed.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 로그인 기능
     *
     * @param loginRequest : userEmail, userPassword
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest, HttpSession session) {

        UserDto userDto = userService.getUserId(loginRequest);

        session.setAttribute("userId", userDto.getId());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/singup")
    public ResponseEntity<UserSignUpDto> createUser(@RequestBody SignUpRequest signUpRequest) {
        UserSignUpDto userSignUpDto = userService.createUser(signUpRequest);

        return new ResponseEntity<>(userSignUpDto, HttpStatus.OK);
    }

    /**
     * 내 프로필 조회
     */
    @GetMapping("/me")
    public ResponseEntity<ProfileDto> getMyProfile(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        ProfileDto myProfile = userService.getMyProfile(userId);

        return new ResponseEntity<>(myProfile, HttpStatus.OK);
    }

    /**
     * 내 프로필 수정
     */
    @PutMapping("/me")
    public ResponseEntity<UserUpdateDto> updateMyProfile(HttpSession session, UpdateUserRequest request) {
        Long userId = (Long) session.getAttribute("userId");
        UserUpdateDto userUpdateDto = userService.updateMyProfile(userId, request);

        return new ResponseEntity<>(userUpdateDto, HttpStatus.OK);
    }

    /**
     * 비밀번호 수정
     */
    @PutMapping("/me/password")
    public ResponseEntity<Void> updateMyPassword(HttpSession session, UpdatePasswordRequest request) {
        Long userId = (Long) session.getAttribute("userId");
        userService.updateMyPassword(userId, request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 다른 유저 프로필 조회
     */

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getUserProfile(@PathVariable Long id) {
        ProfileDto userProfile = userService.getUserProfile(id);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
