package com.sparta.week5project.service;

import com.sparta.week5project.dto.CommentRequestDto;
import com.sparta.week5project.dto.CommentResponseDto;
import com.sparta.week5project.dto.PostResponseDto;
import com.sparta.week5project.entity.*;
import com.sparta.week5project.repository.CommentLikeRepository;
import com.sparta.week5project.repository.CommentRepository;
import com.sparta.week5project.repository.PostRepository;
import com.sparta.week5project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;

@RestController
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentLikeRepository commentLikeRepository;


    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {

        Comment comment = new Comment(requestDto);
        user = findUser(user.getId());
        String username = user.getUsername();
        comment.setUsername(username);
        Post post = findPost(requestDto.getPostid());
        comment.setUser(user);
        comment.setPost(post);
        Comparator<Comment> compare = new Comparator<Comment>() {
            @Override
            public int compare(Comment a, Comment b) {
                return b.getCreatedAt().compareTo(a.getCreatedAt());
            }
        };

        Comment saveComment = commentRepository.save(comment);
        Collections.sort(post.getCommentList(), compare);

        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
        return commentResponseDto;
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(id);
        String username = user.getUsername();

        if (username.equals(comment.getUsername())) {
            comment.update(requestDto);
        } else {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        return new CommentResponseDto(comment);
    }

    public Long deleteComment(Long id, User user) {
        Comment comment = findComment(id);
        String username = user.getUsername();
        if (username.equals(comment.getUsername())||user.getRole()== UserRoleEnum.ADMIN) {
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        return id;
    }

    @Transactional
    public CommentResponseDto likeComment(Long id, User user) {
        Comment comment = findComment(id);
        CommentLike commentLike = new CommentLike(comment,user);
        commentLikeRepository.save(commentLike);
        return new CommentResponseDto(comment);
    }

//    @Transactional
//    public CommentResponseDto likeComment(Long id, User user) {
//        Comment comment = findComment(id);
//        user = findUser(user.getId());
//        comment.addLikeComment(user);
//        return new CommentResponseDto(comment);
//    }

//    private String tokenToName (String tokenValue){
//        String token = jwtUtil.substringToken(tokenValue);
//        if (!jwtUtil.validateToken(token))
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        return jwtUtil.getUserInfoFromToken(token).get("sub").toString();
//    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new NullPointerException("선택한 댓글은 존재하지 않습니다.")
        );
    }
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }



    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("유저 정보를 찾을 수 없습니다.")
        );
    }
}
