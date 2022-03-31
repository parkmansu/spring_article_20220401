package com.sparta.chapter3_1.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long detailId;
    private String contents;
    private String nickname;

}
