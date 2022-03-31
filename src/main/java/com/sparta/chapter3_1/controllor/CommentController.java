package com.sparta.chapter3_1.controllor;

import com.sparta.chapter3_1.dto.CommentRequestDto;
import com.sparta.chapter3_1.model.Comment;
import com.sparta.chapter3_1.repository.CommentRepository;
import com.sparta.chapter3_1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/api/comments")
    public Comment createComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        return commentRepository.save(comment);
    }

    @GetMapping("/api/comments/{id}")
    public List<Comment> getComment() {
        return commentRepository.findAllByOrderByModifiedAtDesc();
    }

    @PutMapping("/api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        commentService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
