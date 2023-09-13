package com.sparta.week5project.entity;

import com.sparta.week5project.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="contents",nullable = false, length = 127)
    private String contents;
    @Column(name="username",nullable = false)
    private String username;

    @ManyToMany(mappedBy = "likeCommentList")
    private List<User> likeUserList = new ArrayList<>();

    public Comment(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
    public void addLikeComment(User user) {
        if (this.likeUserList.contains(user)) {
            this.likeUserList.remove(user);
            user.getLikeCommentList().remove(this);
        } else {
            this.likeUserList.add(user);
            user.getLikeCommentList().add(this);
        }
    }
}