package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.repository.UserRepository;
import com.example.ch4_1_newsfeed.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long getUserId(LoginRequest request) {
        userRepository.findByEmail(request.getEmail());
    }
}
