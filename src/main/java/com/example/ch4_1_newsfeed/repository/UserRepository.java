package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findUserByName(String username);

    default User findUserByUsernameOrElseThrow(String username) {
        return findUserByName(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + username));
    }
    
}
