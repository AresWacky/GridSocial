package com.gridsocial;


import com.gridsocial.model.User;
import com.gridsocial.service.UserService;
import com.nylas.NylasClient;
import com.nylas.models.*;
import com.nylas.models.UrlForAuthenticationConfig;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
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

}