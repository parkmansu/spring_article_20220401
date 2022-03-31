package com.sparta.chapter3_1.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Setter
@Getter
public class SignupRequestDto {
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{3,20}",
            message = "닉네임은 3~20자, 영문 대 소문자, 숫자를 사용하세요.")
    private String username;

    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";

}
