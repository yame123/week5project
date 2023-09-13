package com.sparta.week5project.dto;

import com.sparta.week5project.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private long id;
    private String contents;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private long like;

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.username = comment.getUsername();
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.like = comment.getLikeUserList().size();
    }
}
