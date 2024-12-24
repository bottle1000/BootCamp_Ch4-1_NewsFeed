package com.example.ch4_1_newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Feed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @OneToMany(mappedBy = "feed")
    private List<Photo> photo = new ArrayList<>();

    public Feed() {
    }

    public LocalDateTime getCreatedAt() {
        return null;
    }

    public void updateFeed(String description, List<Photo> photo) {
        this.description = description;
        this.photo = photo;

    }
}
