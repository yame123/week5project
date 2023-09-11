package com.sparta.week5project.controller;

import com.sparta.week5project.dto.SignupRequestDto;
import com.sparta.week5project.exception.RestApiException;
import com.sparta.week5project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping("/user/login")
//    public String login(@RequestBody LoginRequestDto requestDto,  HttpServletResponse res){
//        userService.login(requestDto,res);
//
//        return "{\"msg\" : \"로그인 성공\",\"statusCode\":200}";
//    }

}