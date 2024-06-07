package com.blogapp.repository;

import com.blogapp.entity.Comment;
import com.blogapp.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long > {
    List<Comment> findByPostId(long postId);
}
