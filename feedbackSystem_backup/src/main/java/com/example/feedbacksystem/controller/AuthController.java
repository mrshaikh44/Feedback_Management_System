package com.example.feedbacksystem.controller;

import com.example.feedbacksystem.entity.User;
import com.example.feedbacksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Show Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Show Register Page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle Registration
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    // Handle Login
    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {

        User user = userService.loginUser(username, password);

        if (user != null) {
            if (user.getRole().equals("ADMIN")) {
                return "redirect:/admin";
            }
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}