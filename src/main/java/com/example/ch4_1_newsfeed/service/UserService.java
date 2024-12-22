package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.dto.user.ProfileDto;
import com.example.ch4_1_newsfeed.dto.user.UserDto;
import com.example.ch4_1_newsfeed.dto.user.UserSignUpDto;
import com.example.ch4_1_newsfeed.dto.user.UserUpdateDto;
import com.example.ch4_1_newsfeed.entity.Feed;
import com.example.ch4_1_newsfeed.entity.User;
import com.example.ch4_1_newsfeed.repository.FeedRepository;
import com.example.ch4_1_newsfeed.repository.UserRepository;
import com.example.ch4_1_newsfeed.request.LoginRequest;
import com.example.ch4_1_newsfeed.request.SignUpRequest;
import com.example.ch4_1_newsfeed.request.UpdatePasswordRequest;
import com.example.ch4_1_newsfeed.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    public UserDto getUserId(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        // 비밀번호 암호화 된걸로 매치 예정

        return UserDto.from(user);
    }

    public UserSignUpDto createUser(SignUpRequest request) {
        User user = User.createUser(request);
        userRepository.save(user);

        return UserSignUpDto.from(user);
    }

    public ProfileDto getMyProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("내 정보가 존재하지 않습니다."));
        List<Feed> feedList = feedRepository.findAllByUserId(user.getId());

        return ProfileDto.from(user, feedList);
    }

    // 트랜잭셔널 추가
    @Transactional
    public UserUpdateDto updateMyProfile(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("내 정보가 존재하지 않습니다."));
        user.updateUser(request);
        return UserUpdateDto.from(user);
    }

    @Transactional
    public void updateMyPassword(Long userId, UpdatePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("내 정보가 존재하지 않습니다."));
        user.updateUserPassword(request);
    }
}
