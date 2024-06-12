package com.gridsocial.auth;

import com.gridsocial.model.User;
import com.gridsocial.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    public final UserService userService;

    private final AuthenticationService service;
    private final HttpSession httpSession;


    @PostMapping("/pre-register")
    public String preRegister(RegisterRequest request, RedirectAttributes redirectAttributes) {

        String username = request.getUsername();
        String email = request.getEmail();
        String password = request.getPassword();

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
        }

        // Store the registration request in session
        httpSession.setAttribute("registerRequest", request);

        // Redirect to Nylas authentication
        return "redirect:/nylas/auth";
    }

    @PostMapping("/register")
    public String register(RegisterRequest request, RedirectAttributes redirectAttributes) {

        service.register(request);
        return "redirect:/login";
    }

    @PostMapping("/authenticate")
    public String authenticate(AuthenticationRequest request, RedirectAttributes redirectAttributes) {
        AuthenticationResponse resp = service.authenticate(request);

        if (resp != null) {
            String username = resp.getUsername();  // Get the username from the response
            redirectAttributes.addAttribute("username", username);
            return "redirect:/" + username + "/personal";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Authentication failed. Please sign up.");
            return "redirect:/signup";
        }
    }


}
