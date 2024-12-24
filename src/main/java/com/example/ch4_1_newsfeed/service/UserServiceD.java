package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.dto.user.response.RelationshipResponseDto;
import com.example.ch4_1_newsfeed.entity.User;
import com.example.ch4_1_newsfeed.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceD {

    UserRepository userRepository;

    public RelationshipResponseDto follow(Long id, HttpSession session) {

        User following = (User) session.getAttribute("user");
        User followed = userRepository.findById(id).orElseThrow();

        if (true) {
            return new RelationshipResponseDto(following, followed, "you followed " + followed.getName());
        }

        return new RelationshipResponseDto(following, followed, "you unfollowed " + following.getName());
    }

    private void unfollow(User following, User followed){

    }

    private void doFollow(User following, User followed){}
}
