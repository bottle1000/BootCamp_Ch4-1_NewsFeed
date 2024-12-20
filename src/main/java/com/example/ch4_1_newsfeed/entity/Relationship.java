package com.example.ch4_1_newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

}
