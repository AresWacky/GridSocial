package com.gridsocial.nylas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class NylasAuthService {

    private final RestTemplate restTemplate;
    private final Environment environment;

    @Autowired
    public NylasAuthService(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    public String exchangeCodeForGrantId(String code) {
        String apiUri = environment.getProperty("nylas.api.uri");
        String clientId = environment.getProperty("nylas.client.id");
        String clientSecret = environment.getProperty("nylas.client.secret");
        String redirectUri = environment.getProperty("nylas.redirect.uri");

        NylasTokenRequest tokenRequest = new NylasTokenRequest(clientId, clientSecret, "authorization_code", code, redirectUri, "nylas");
        ResponseEntity<NylasTokenResponse> response = restTemplate.postForEntity(apiUri + "/oauth/token", tokenRequest, NylasTokenResponse.class);
        return Objects.requireNonNull(response.getBody()).getGrantId();
    }
}
