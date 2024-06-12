package com.gridsocial.nylas;

import com.gridsocial.auth.RegisterRequest;
import com.nylas.NylasClient;
import com.nylas.models.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.List;

@RestController
public class NylasAuthController {

    @Value("${nylas.client.id}")
    private String clientId;

    private final NylasClient nylasClient;
    private final NylasAuthService nylasAuthService;

    @Autowired
    public NylasAuthController(NylasClient nylasClient, NylasAuthService nylasAuthService) {
        this.nylasClient = nylasClient;
        this.nylasAuthService = nylasAuthService;
    }

    @GetMapping("/nylas/auth")
    public RedirectView authenticate() {
        List<String> scope = Collections.singletonList("https://www.googleapis.com/auth/calendar");

        UrlForAuthenticationConfig config = new UrlForAuthenticationConfig(
                clientId,
                "http://localhost:8080/nylas/callback",
                AccessType.ONLINE,
                AuthProvider.GOOGLE,
                Prompt.DETECT,
                scope,
                true,
                "sQ6vFQN",
                "swag@nylas.com"
        );

        String url = nylasClient.auth().urlForOAuth2(config);

        return new RedirectView(url);
    }

    @GetMapping("/nylas/callback")
    public String handleCallback(@RequestParam("code") String code, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        // Exchange the code for grant ID
        String grantId = nylasAuthService.exchangeCodeForGrantId(code);

        // Retrieve the stored registration request from session
        RegisterRequest request = (RegisterRequest) httpSession.getAttribute("registerRequest");

        // Check if request is null (session expired or not found)
        if (request == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Session expired or request not found.");
            return "redirect:/signup";
        }

        // Update the registration request with the grant ID
        request.setNylasGrantId(grantId);

        // Redirect to the registration endpoint with the updated request
        httpSession.removeAttribute("registerRequest"); // Remove stored request from session
        httpSession.setAttribute("registerRequest", request); // Store updated request in session
        return "redirect:/register"; // Redirect to the register page
    }
}
