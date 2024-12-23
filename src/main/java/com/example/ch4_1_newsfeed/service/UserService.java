package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.dto.user.response.*;
import com.example.ch4_1_newsfeed.dto.user.request.*;
import com.example.ch4_1_newsfeed.entity.Feed;
import com.example.ch4_1_newsfeed.entity.User;
import com.example.ch4_1_newsfeed.repository.FeedRepository;
import com.example.ch4_1_newsfeed.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    public UserResponseDto getUserId(LoginUserRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail());
        // 비밀번호 암호화 된걸로 매치 예정

        return UserResponseDto.from(user);
    }

    public SignUpUserResponseDto createUser(SignUpUserRequestDto request) {
        User user = User.createUser(request);
        userRepository.save(user);

        return SignUpUserResponseDto.from(user);
    }

    public ProfileUserResponseDto getMyProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("내 정보가 존재하지 않습니다."));
        List<Feed> feedList = feedRepository.findAllByUserId(user.getId());

        return ProfileUserResponseDto.from(user, feedList);
    }

    // 트랜잭셔널 추가
    @Transactional
    public UpdateUserResponseDto updateMyProfile(Long userId, UpdateUserRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("내 정보가 존재하지 않습니다."));
        user.updateUser(request);
        return UpdateUserResponseDto.from(user);
    }

    @Transactional
    public void updateMyPassword(Long userId, UpdatePasswordUserRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("내 정보가 존재하지 않습니다."));
        user.updateUserPassword(request);
    }

    public ProfileUserResponseDto getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("찾으시는 사용자가 존재하지 않습니다."));
        List<Feed> feedList = feedRepository.findAllByUserId(user.getId());
        return ProfileUserResponseDto.from(user, feedList);
    }

    public void deleteUser(Long userId, DeleteUserRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("내 정보가 존재하지 않습니다."));

        if (user.getPassword() != request.getPassword()) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(user);
    }
}
