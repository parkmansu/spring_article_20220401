package com.sparta.chapter3_1.controllor;

import com.sparta.chapter3_1.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ViewController {

    @GetMapping("/api/memos/detail")
    public String getDetail(@RequestParam("id") Long id) {
        return "detail";
    }

    @GetMapping("/username")
    public String getUsername(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        System.out.println(userDetails.getUsername());
        return userDetails.getUsername();

    }

}
