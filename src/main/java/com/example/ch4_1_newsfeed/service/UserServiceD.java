package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.entity.User;
import com.example.ch4_1_newsfeed.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceD {

    UserRepository userRepository;

    public void following(Long id, HttpSession session) {

        User follower = (User) session.getAttribute("user");
        User followee = userRepository.findById(id).orElseThrow();

    }
}
