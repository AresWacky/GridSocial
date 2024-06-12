package com.gridsocial.nylas;

import com.nylas.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nylas")
public class NylasController {

    private final NylasService nylasService;

    @Autowired
    public NylasController(NylasService nylasService) {
        this.nylasService = nylasService;
    }

    @GetMapping("/exchange-token")
    public NylasTokenResponse exchangeToken(@RequestParam String code) {
        return nylasService.exchangeCodeForToken(code);
    }

    @PostMapping("/create-calendar")
    public Calendar createCalendar(@RequestBody CreateCalendarRequest createCalendarRequest) {
        try {
            return nylasService.createCalendar(
                    createCalendarRequest.getName(),
                    createCalendarRequest.getDescription(),
                    createCalendarRequest.getLocation(),
                    createCalendarRequest.getTimezone(),
                    createCalendarRequest.getMetadata()
            );
        } catch (NylasSdkTimeoutError | NylasApiError e) {
            throw new RuntimeException("Failed to create calendar", e);
        }
    }
}