package com.springAi.LamNexus.provider;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@Service
public class GroqProvider {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${groq.api-key:NOT_FOUND}")
    private String apiKey;
    @Value("${groq.base-url:NOT_FOUND}")
    private String baseUrl;


    public String generate(String model, String prompt) {
        System.out.println("Groq API Key Present: " + (apiKey != null));
        System.out.println("Groq Base URL: " + baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );
        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        baseUrl + "/v1/chat/completions",
                        request,
                        Map.class
                );

        Map choice =
                (Map) ((List) response.getBody()
                        .get("choices"))
                        .get(0);

        Map message =
                (Map) choice.get("message");

        return message.get("content")
                .toString();

    }

}
