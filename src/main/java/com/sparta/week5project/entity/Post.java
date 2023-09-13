package com.sparta.week5project.entity;


import com.sparta.week5project.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="post")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title",nullable = false)
    private String title;
    @Column(name="contents",nullable = false, length = 511)
    private String contents;
    @Column(name="username",nullable = false)
    private String username;

    @OneToMany(mappedBy = "post",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id") // users 테이블에 food_id 컬럼
    private User user;

    @OneToMany(mappedBy = "postLike",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<PostLike> PostLikeList = new ArrayList<>();



    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }


    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }



}
