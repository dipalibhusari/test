package com.blogapp.payload;

import com.blogapp.entity.Post;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class PostWithCommentDto {
    private PostDto post;
    /*private long id;
    private String name;
    private String message;*/
    private List<CommentDto> commentDtos;
}
