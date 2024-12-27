package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.model.dto.feed.response.FindAllFeedResponseDto;
import com.example.ch4_1_newsfeed.model.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    /**
     * 유저 ID 기반으로 피드 데이터를 가져옴
     * todo 왜 null임?
     */

    List<Feed> findByUser_Id(Long userId);

    default Feed findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    /**
     * userId와 feedId 조건을 만족하는 데이터를 가져옴
     */
    Feed findByIdAndId(Long userId, Long feedId);

    /**
     * todo 2track
     * -> following_id 컬럼이 followee_id로 잘못 작성되어 있었음
     */
    @NativeQuery(
        value = "select " +
            "f.id as feed_id, f.user_id as user_id, f.contents as contents, f.created_at as createdAt, GROUP_CONCAT(p.URL) as photos " +
            "from Feed f " +
            "left join Photo p on p.feed_id = f.id " +
            "LEFT JOIN" +
                "Relationship r ON r.following_id = f.user_id" +
                "WHERE f.user_id = :userId" +
            "group by f.id " +
            "order by created_at DESC",
        sqlResultSetMapping = "FindAllFeedResponseDtoMapping")

    Page<FindAllFeedResponseDto> findFeedsByUserRelationships(@Param("userId") Long userId, Pageable pageable);

}


