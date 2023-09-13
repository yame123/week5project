package com.sparta.week5project.controller;

import com.sparta.week5project.dto.PostRequestDto;
import com.sparta.week5project.dto.PostResponseDto;
import com.sparta.week5project.exception.RestApiException;
import com.sparta.week5project.security.UserDetailsImpl;
import com.sparta.week5project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/post")//모든 포스팅
    public List<PostResponseDto> getPosts(){

        return postService.getPosts();
    }


    @GetMapping("/post/{id}")//한개 포스팅
    public PostResponseDto getOnePost(@PathVariable Long id){

        return postService.getOnePost(id);
    }

    @PostMapping("/post")//포스팅 등록
    public PostResponseDto posting(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return postService.posting(postRequestDto,userDetails.getUser());
    }
    //@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return postService.updatePost(id,postRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(id, userDetails.getUser());
    }

    @GetMapping("/post/like/{id}")// 포스트 좋아요
    public PostResponseDto likePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return postService.likePost(id,userDetails.getUser());
    }
}
