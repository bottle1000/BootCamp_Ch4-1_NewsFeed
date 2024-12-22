package com.example.ch4_1_newsfeed.entity;

import com.example.ch4_1_newsfeed.request.SignUpRequest;
import com.example.ch4_1_newsfeed.request.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_picture")
    private String profilePicture;

    public User() {

    }

    public static User createUser(SignUpRequest request) {
        return new User(
                null,
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getPictureUrl()
        );
    }

    public void updateUser(UpdateUserRequest request) {
        if (request.getName() != null) {
            this.name = request.getName();
        }

        if (request.getPictureUrl() != null) {
            this.profilePicture = request.getPictureUrl();
        }
    }
}









