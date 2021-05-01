package com.meng.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meng.blog.dto.*;
import com.meng.blog.exception.PostNotFoundException;
import com.meng.blog.mapper.*;
import com.meng.blog.model.*;
import com.meng.blog.repo.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PostRepository postRepository;
    @Autowired
    private  AuthService authService;
    @Autowired
    private  CommentMapper commentMapper;
    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private  MailContentBuilder mailContentBuilder;
    @Autowired
    private  MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(authService.getCurrentUser() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(Collectors.toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}