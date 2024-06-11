package com.gridsocial;


import com.gridsocial.model.User;
import com.gridsocial.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AppController {

    @GetMapping("/login")
    public String login() {
        return "/home/login-page";
    }

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "/home/signup-page";
    }

    @PostMapping("/signup-request")
    public String signup(@RequestParam String username, @RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {

        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Missing Fields");
            return "redirect:/signup";
        }
        if (username.length() < 4) {
            redirectAttributes.addFlashAttribute("errorMessage", "Username must not be less than 4 characters.");
            return "redirect:/signup";
        }
        if (password.length() < 7) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password must not be less than 7 letters.");
            return "redirect:/signup";
        }

        Optional<User> existingUser = userService.findByEmail(email);
        if (existingUser.isPresent()) {
            // If user already exists, redirect back to the sign-up page with an error message
            redirectAttributes.addFlashAttribute("errorMessage", "User already exists");
            return "redirect:/signup";
        } else {
            // Create a new user and save it to the database
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password);
            userService.saveUser(newUser);

            // Redirect to login-page page
            return "redirect:/login";
        }
    }



}