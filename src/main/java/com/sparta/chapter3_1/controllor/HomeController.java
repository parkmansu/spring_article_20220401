package com.sparta.chapter3_1.controllor;

import com.sparta.chapter3_1.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
            model.addAttribute("username", userDetails.getUsername());
            System.out.println(userDetails.getUsername());
            return "index";
    }
}
