package com.example.ch4_1_newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Relationship {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User following;

}
