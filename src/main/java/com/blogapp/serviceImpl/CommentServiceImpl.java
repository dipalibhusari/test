package com.blogapp.serviceImpl;

import com.blogapp.entity.Comment;
import com.blogapp.entity.Post;
import com.blogapp.payload.CommentDto;
import com.blogapp.payload.PostDto;
import com.blogapp.payload.PostWithCommentDto;
import com.blogapp.repository.CommentRepo;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;
    private ModelMapper modelMapper;
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);

        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    public PostWithCommentDto getAllCommentsByPostId(long postId){
        Post post = postRepository.findById(postId).get();

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        List<Comment> comments = commentRepo.findByPostId(postId);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        PostWithCommentDto postWithCommentDto = new PostWithCommentDto();

        postWithCommentDto.setCommentDtos(dtos);
        postWithCommentDto.setPost(dto);

        return postWithCommentDto;
    }
    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment comment){
       return modelMapper.map(comment,CommentDto.class);
    }
}
