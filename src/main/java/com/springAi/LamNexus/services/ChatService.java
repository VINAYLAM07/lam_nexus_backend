package com.springAi.LamNexus.services;

import com.springAi.LamNexus.provider.OpenRouterProvider;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final OpenRouterProvider openRouterProvider;;

    public ChatService(OpenRouterProvider openRouterProvider) {
        this.openRouterProvider = openRouterProvider;
    }


    public String ask(String provider,String model, String prompt) {

        return switch (provider.toLowerCase()) {
//            case "gpt" -> "openai/gpt-oss-20b:free";
//            case "claude" -> "anthropic/claude-3-haiku";
//
//            case "gemini" -> "google/gemini-2.5-flash";
//
//            case "llama" -> "meta-llama/llama-3.3-70b-instruct";
            case "openrouter" ->
                    openRouterProvider.generate(
                            model,
                            prompt
                    );

            default ->
                    throw new RuntimeException(
                            "Unsupported provider: "
                                    + provider
                    );
        };
    }
}
