package com.gridsocial.nylas;

import com.nylas.NylasClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NylasConfig {

    @Bean
    public NylasClient nylasClient(@Value("${nylas.api.key}") String apiKey, @Value("${nylas.api.uri}") String apiUri) {
        return new NylasClient.Builder(apiKey).apiUri(apiUri).build();
    }
}
