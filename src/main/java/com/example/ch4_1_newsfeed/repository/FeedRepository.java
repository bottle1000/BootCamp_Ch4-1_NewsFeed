package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.dto.feed.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    /**
     * 유저 ID 기반으로 피드 데이터를 가져옴
     */
    List<Feed> findAllByUserId(Long userId, Pageable pageable);

    default Feed findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    /**
     * user_id와 feed_id 조건을 만족하는 데이터를 가져옴
     */
    Feed findByIdAndId(Long user_id, Long feed_id);

    @NativeQuery(
        value = "select " +
            "f.id as feed_id, f.user_id as user_id, f.contents as contents, f.createdAt as created_at, GROUP_CONCAT(p.URL) as photos " +
            "from Feed f " +
            "left join Photo p on p.feed_id = f.id " +
            "left join Relationship  r on r.follower_id = :userId " +
            "group by f.id " +
            "order by f.createdAt DESC",
        sqlResultSetMapping = "FindAllFeedResponseDtoMapping")
    List<FindAllFeedResponseDto> findFeedsByUserRelationships(@Param("userId") Long userId, Pageable pageable);

}


