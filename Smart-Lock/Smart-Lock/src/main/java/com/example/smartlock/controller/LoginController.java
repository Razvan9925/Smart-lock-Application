package com.example.smartlock.controller;

import com.example.smartlock.dto.UserDto;
import com.example.smartlock.entity.User;
import com.example.smartlock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    @GetMapping()
    public String login() {
        return "login/login";
    }


    @GetMapping("/error")
    public String loginError() {
        return "login/LoginError";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login/register";
    }

    @PostMapping("/registerUser")
    public String userRegister(@Valid @ModelAttribute("user") UserDto user, Errors errors) {
        if (errors.hasErrors()) {
            return "login/register";
        } else {
            userService.addUsers(user);
            return "redirect:/login";
        }

    }
}
