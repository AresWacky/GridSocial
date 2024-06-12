package com.gridsocial.nylas;

import com.nylas.NylasClient;
import com.nylas.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NylasService {

    private final NylasClient nylasClient;

    @Value("${nylas.grant.id}")
    private String grantId;

    @Value("${nylas.client.id}")
    private String clientId;

    @Value("${nylas.client.secret}")
    private String clientSecret;

    @Value("${nylas.redirect.uri}")
    private String redirectUri;

    public NylasService(NylasClient nylasClient) {
        this.nylasClient = nylasClient;
    }

    public NylasTokenResponse exchangeCodeForToken(String code) {
        // Create the request body
        NylasTokenRequest tokenRequest = new NylasTokenRequest(clientId, clientSecret, "authorization_code", code, redirectUri, "nylas");

        // POST request to Nylas /token endpoint
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NylasTokenResponse> response = restTemplate.postForEntity("https://api.nylas.com/connect/token", tokenRequest, NylasTokenResponse.class);

        return response.getBody();
    }

    public Calendar createCalendar(String name, String description, String location, String timeZone, Map<String, String> metadata)
            throws NylasSdkTimeoutError, NylasApiError {
        CreateCalendarRequest requestBody = new CreateCalendarRequest(
                name,
                description,
                location,
                timeZone,
                metadata
        );

        Response<Calendar> calendarResponse = nylasClient.calendars().create(grantId, requestBody);
        return calendarResponse.getData();
    }

    // Additional methods to interact with the Nylas Calendar API
}