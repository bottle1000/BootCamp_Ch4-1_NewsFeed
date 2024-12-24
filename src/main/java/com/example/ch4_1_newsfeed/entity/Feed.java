package com.example.ch4_1_newsfeed.entity;

import com.example.ch4_1_newsfeed.dto.feed.FeedRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "feed")
@EntityListeners(AuditingEntityListener.class)
public class Feed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    //private String contents;

    @Column(columnDefinition = "longtext")
    private String contents;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }





//    @Column(nullable = false)
//    @OneToMany(mappedBy = "feed")
//    private List<Photo> photo = new ArrayList<>();

    public Feed() {
    }

    public Feed(String contents) {

        this.contents = contents;
    }

    public void setUser(User user) {

        this.user = user;
    }
}



