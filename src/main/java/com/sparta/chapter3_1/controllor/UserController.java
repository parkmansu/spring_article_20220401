package com.sparta.chapter3_1.controllor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.chapter3_1.Validator.CheckUsernameValidator;
import com.sparta.chapter3_1.dto.SignupRequestDto;
import com.sparta.chapter3_1.service.KakaoUserService;
import com.sparta.chapter3_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;


@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;
    private final CheckUsernameValidator checkUsernameValidator;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService, CheckUsernameValidator checkUsernameValidator) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
        this.checkUsernameValidator = checkUsernameValidator;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("msg","로그인 페이지로 갈 수 없습니다.");
            model.addAttribute("username",principal.getName());
            return "index";
        }
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("msg","회원가입 페이지로 갈 수 없습니다.");
            model.addAttribute("username", principal.getName());
            return "index";
        }
        return "signup";
    }

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder // 특정 컨트롤러에서 바인딩 또는 검증 설정을 변경하고 싶을 때 사용한다.
    public void validatorBinder(WebDataBinder binder) { // HTTP 요청 정보를 컨트롤러 메소드의 파라미터나 모델에 바인딩할 때 사용되는 바인딩 객체
        binder.addValidators(checkUsernameValidator);
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model) {

        System.out.println(requestDto.getUsername());
        if(errors.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 값을 유지 */
            model.addAttribute("requestDto", requestDto);
            System.out.println(errors);

            /* 유효성 통과 못한 필드와 메세지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            System.out.println(validatorResult);
            /* 회원가입 페이지로 다시 리턴 */
            return "signup";
        }

        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }


    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
    // authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);

        return "redirect:/";
    }

}