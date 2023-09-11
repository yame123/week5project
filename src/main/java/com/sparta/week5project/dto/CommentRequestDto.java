package com.sparta.week5project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank
    private Long postid;
    @NotBlank
    private String contents;
}
