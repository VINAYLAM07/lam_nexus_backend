package com.springAi.LamNexus.services;

import com.springAi.LamNexus.provider.GeminiProvider;
import com.springAi.LamNexus.provider.GroqProvider;
import com.springAi.LamNexus.provider.OllamaProvider;
import com.springAi.LamNexus.provider.OpenRouterProvider;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final OpenRouterProvider openRouterProvider;;
    private final GeminiProvider geminiProvider;
    private final GroqProvider groqProvider;

    private final OllamaProvider ollamaProvider;

    public ChatService(OpenRouterProvider openRouterProvider, GeminiProvider geminiProvider, GroqProvider groqProvider, OllamaProvider ollamaProvider) {
        this.openRouterProvider = openRouterProvider;
        this.geminiProvider = geminiProvider;
        this.groqProvider = groqProvider;
        this.ollamaProvider = ollamaProvider;
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
            case "gemini" ->
                    geminiProvider.generate(
                            model,
                            prompt
                    );
            case "groq" ->
                    groqProvider.generate(
                            model,
                            prompt);
            case "ollama" ->
                ollamaProvider.generate(
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
