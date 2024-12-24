package com.example.ch4_1_newsfeed.service;

import com.example.ch4_1_newsfeed.dto.user.response.RelationshipResponseDto;
import com.example.ch4_1_newsfeed.entity.Relationship;
import com.example.ch4_1_newsfeed.entity.User;
import com.example.ch4_1_newsfeed.repository.RelationshipRepository;
import com.example.ch4_1_newsfeed.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceD {

    UserRepository userRepository;
    RelationshipRepository relationshipRepository;

    public RelationshipResponseDto follow(Long followeeId, HttpSession session) {

        Long userId = (Long) session.getAttribute("user");
        User following = userRepository.findById(userId).orElseThrow();
        User followed = userRepository.findById(followeeId).orElseThrow();

        Optional<Relationship> foundRelationship =
                relationshipRepository.findRelationshipByFollower_IdAndFollowee_id
                        (following.getId(), followed.getId());

        if (foundRelationship.isEmpty()) {

            Relationship relationship = new Relationship(following, followed);
            relationshipRepository.save(relationship);
            return new RelationshipResponseDto(following,followed,"you followed " + followed.getName());

        }

        relationshipRepository.delete(foundRelationship.get());
        return new RelationshipResponseDto(following, followed, "you unfollowed " + followed.getName());
    }
}
