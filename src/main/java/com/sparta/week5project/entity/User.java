package com.sparta.week5project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") // users 테이블에 food_id 컬럼
    private List<Post> postList = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") // users 테이블에 food_id 컬럼
    private List<Comment> commentList = new ArrayList<>();




    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "postlike", // 중간 테이블 생성
            joinColumns = @JoinColumn(name = "user_id"), // 현재 위치인 Food Entity 에서 중간 테이블로 조인할 컬럼 설정
            inverseJoinColumns = @JoinColumn(name = "post_id")) // 반대 위치인 User Entity 에서 중간 테이블로 조인할 컬럼 설정
    private List<Post> likePostList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "commentlike", // 중간 테이블 생성
            joinColumns = @JoinColumn(name = "user_id"), // 현재 위치인 Food Entity 에서 중간 테이블로 조인할 컬럼 설정
            inverseJoinColumns = @JoinColumn(name = "comment_id")) // 반대 위치인 User Entity 에서 중간 테이블로 조인할 컬럼 설정
    private List<Comment> likeCommentList = new ArrayList<>();





//    public void addLikeCommentList(Comment comment) {
//        this.likeCommentList.add(comment); // 외래 키(연관 관계) 설정
//    }


    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }



//    public void addLikeComment(Comment comment) {
//        if (this.likeCommentList.contains(comment)) {
//            this.likeCommentList.remove(comment);
//            comment.getLikeUserList().remove(this);
//        } else {
//            this.likeCommentList.add(comment);
//            comment.getLikeUserList().add(this);
//        }
//    }

}