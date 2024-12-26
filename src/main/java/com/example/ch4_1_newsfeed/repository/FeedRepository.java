package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.dto.user.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    /**
     * 유저 ID 기반으로 피드 데이터를 가져옴
     */
    List<Feed> findAllByUserId(Long userId);

    default Feed findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    /**
     * user_id와 feed_id 조건을 만족하는 데이터를 가져옴
     */
    Feed findByIdAndId(Long user_id, Long feed_id);

    @Query("select new com.example.ch4_1_newsfeed.dto.user.response.FindAllFeedResponseDto(" +
            "f.id, u.id, f.contents, f.createdAt, " +
            "(select p.feed.id from Photo p where p.feed.id = f.id)" +
            ") " +
            "from Feed f " +
            "join f.user u " +
            "order by f.createdAt DESC ")
    Page<FindAllFeedResponseDto> findByPagingAllFeeds(Pageable pageable);

}


