package com.example.ch4_1_newsfeed.entity;

import com.example.ch4_1_newsfeed.dto.user.request.SignUpUserRequestDto;
import com.example.ch4_1_newsfeed.dto.user.request.UpdatePasswordUserRequestDto;
import com.example.ch4_1_newsfeed.dto.user.request.UpdateUserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    public static User createUser(SignUpUserRequestDto request, String encodedPassword) {
        return new User(
                null,
                request.getName(),
                request.getEmail(),
                encodedPassword,
                request.getPhoneNumber(),
                request.getPictureUrl()
        );
    }

    public void updateUser(UpdateUserRequestDto request) {
        if (request.getName() != null) {
            this.name = request.getName();
        }

        if (request.getPictureUrl() != null) {
            this.profilePicture = request.getPictureUrl();
        }
    }

    public void updateUserPassword(UpdatePasswordUserRequestDto request) {
        if (request.getPassword() != null) {
            this.password = request.getPassword();
        }
    }
}









