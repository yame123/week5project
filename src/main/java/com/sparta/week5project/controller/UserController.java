package com.sparta.week5project.controller;

import com.sparta.week5project.dto.SignupRequestDto;
import com.sparta.week5project.security.UserDetailsImpl;
import com.sparta.week5project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/user/signup")
    public String signup(@Valid @RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "{\"msg\" : \"회원가입 성공\",\"statusCode\":200}";
    }

    @DeleteMapping("/user/signout")
    public String signout(@AuthenticationPrincipal UserDetailsImpl userDetails){
        userService.signout(userDetails.getUser());
        return "{\"msg\" : \"회원탈퇴 성공\",\"statusCode\":200}";
    }

//    @PostMapping("/user/login")
//    public String login(@RequestBody LoginRequestDto requestDto,  HttpServletResponse res){
//        userService.login(requestDto,res);
//
//        return "{\"msg\" : \"로그인 성공\",\"statusCode\":200}";
//    }

}