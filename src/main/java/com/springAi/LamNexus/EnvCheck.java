package com.springAi.LamNexus;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvCheck {

    @Value("${OPENROUTER_API_KEY:NOT_FOUND}")
    private String key;

    @PostConstruct
    public void init() {

        System.out.println(
                "OPENROUTER_API_KEY exists = "
                        + !"NOT_FOUND".equals(key)
        );
    }
}