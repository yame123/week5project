package com.sparta.week5project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="commentlike")
@NoArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userLike;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment commentLike;

    public CommentLike(Comment comment, User user) {
        this.commentLike = comment;
        this.userLike = user;
    }
}
