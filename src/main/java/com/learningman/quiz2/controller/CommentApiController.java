package com.learningman.quiz2.controller;

import com.learningman.quiz2.dto.CommentDto;
import com.learningman.quiz2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    CommentService commentService;
    // 1. 댓글 조회
    @GetMapping("/api/words/{wordId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long wordId){
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(wordId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 2. 댓글 생성
    @PostMapping("/api/words/{wordId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long wordId, @RequestBody CommentDto dto){
        // 서비스에 위임
        CommentDto createdDto = commentService.create(wordId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    // 3. 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        // 서비스에 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    // 4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // 서비스에 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
