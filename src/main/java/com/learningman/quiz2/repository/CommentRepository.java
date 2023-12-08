package com.learningman.quiz2.repository;

import com.learningman.quiz2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value = "select * from comment where word_id = :wordId", nativeQuery = true)
    List<Comment> findByWordId(Long wordId);

    // 특정 닉네임의 모든 댓글 조회
    // List<Comment> findByNickName(String nickname);
}
