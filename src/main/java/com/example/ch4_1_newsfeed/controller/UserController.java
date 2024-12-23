package com.example.ch4_1_newsfeed.controller;


import com.example.ch4_1_newsfeed.dto.user.response.*;
import com.example.ch4_1_newsfeed.dto.user.request.*;
import com.example.ch4_1_newsfeed.service.UserService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginUserRequestDto loginRequest, HttpSession session) {

        UserResponseDto userDto = userService.loginUser(loginRequest);
        session.setAttribute("userId", userDto.getId());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> createUser(@RequestBody SignUpUserRequestDto signUpRequest) {
        SignUpUserResponseDto userSignUpDto = userService.createUser(signUpRequest);

        return new ResponseEntity<>(userSignUpDto, HttpStatus.OK);
    }

    /**
     * 내 프로필 조회
     */
    @GetMapping("/me")
    public ResponseEntity<ProfileUserResponseDto> getMyProfile(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        ProfileUserResponseDto myProfile = userService.getMyProfile(userId);

        return new ResponseEntity<>(myProfile, HttpStatus.OK);
    }

    /**
     * 내 프로필 수정
     */
    @PutMapping("/me")
    public ResponseEntity<UpdateUserResponseDto> updateMyProfile(HttpSession session, UpdateUserRequestDto request) {
        Long userId = (Long) session.getAttribute("userId");
        UpdateUserResponseDto userUpdateDto = userService.updateMyProfile(userId, request);

        return new ResponseEntity<>(userUpdateDto, HttpStatus.OK);
    }

    /**
     * 비밀번호 수정
     */
    @PutMapping("/me/password")
    public ResponseEntity<Void> updateMyPassword(HttpSession session, UpdatePasswordUserRequestDto request) {
        Long userId = (Long) session.getAttribute("userId");
        userService.updateMyPassword(userId, request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 다른 유저 프로필 조회
     */

    @GetMapping("/{id}")
    public ResponseEntity<ProfileUserResponseDto> getUserProfile(@PathVariable Long id) {
        ProfileUserResponseDto userProfile = userService.getUserProfile(id);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteUser(DeleteUserRequestDto request, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        userService.deleteUser(userId, request);

        return ResponseEntity.ok("성공적으로 탈퇴 되었습니다.");
    }
}
