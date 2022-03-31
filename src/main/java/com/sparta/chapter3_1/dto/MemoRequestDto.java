package com.sparta.chapter3_1.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private String title;
    private String contents;
    private String nickname;

    public MemoRequestDto() {
    }
}
