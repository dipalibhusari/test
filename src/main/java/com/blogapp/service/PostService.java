package com.blogapp.service;

import com.blogapp.payload.PostDto;

import java.util.List;


public interface PostService {
    public PostDto createPost(PostDto postDto);

    void deletePost(long id);
    List<PostDto> fetchAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);
    public PostDto getPostById(long id);
}
