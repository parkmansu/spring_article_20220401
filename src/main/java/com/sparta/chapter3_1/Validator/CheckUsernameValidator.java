package com.sparta.chapter3_1.Validator;

import com.sparta.chapter3_1.dto.SignupRequestDto;
import com.sparta.chapter3_1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<SignupRequestDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(SignupRequestDto dto, Errors errors) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            errors.rejectValue("username",
                                "닉네임 중복 오류",
                                "이미 사용중인 닉네임 입니다.");
        }
    }
}