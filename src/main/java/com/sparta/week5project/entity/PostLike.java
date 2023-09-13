package com.sparta.week5project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="postlike")
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userLike;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postLike;

    public PostLike(Post post, User user) {
        this.postLike = post;
        this.userLike = user;
    }
}
