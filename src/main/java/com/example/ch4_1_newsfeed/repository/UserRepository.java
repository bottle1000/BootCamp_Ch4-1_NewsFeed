package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * null 예외를 처리하기 위해 Optional을 추가함
     */
    Optional<User> findByEmail(String email);
    
}
