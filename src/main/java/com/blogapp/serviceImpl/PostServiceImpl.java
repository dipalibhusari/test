package com.blogapp.serviceImpl;

import com.blogapp.entity.Post;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.payload.PostDto;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);

        PostDto dto = mapToDto(savedPost);

        return dto;
    }

    @Override
    public void deletePost(long id) {

        postRepository.deleteById(id);
    }

    public PostDto getPostById(long id){
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with id :"+id));
        return mapToDto(post);
    }

    @Override
    public List<PostDto> fetchAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        // List<Post> listOfPosts = postRepository.findAll();
        Page<Post> all = postRepository.findAll(pageable);
        List<Post> listOfPosts = all.getContent();
        List<PostDto> postDtos = listOfPosts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return postDtos;
    }

    Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }
    PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
        return dto;
    }
}
