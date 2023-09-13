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

    @ManyToOne
    @JoinColumn(name = "post_id") // users 테이블에 food_id 컬럼
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id") // users 테이블에 food_id 컬럼
    private User user;

    @OneToMany(mappedBy = "commentLike",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<CommentLike> commentLikeList = new ArrayList<>();

    public Comment(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

}