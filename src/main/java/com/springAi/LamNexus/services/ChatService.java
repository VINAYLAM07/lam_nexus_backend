package com.springAi.LamNexus.services;

import org.springframework.ai.openai.OpenAiChatModel;

public class ChatService {

    private final OpenAiChatModel model;

    public ChatService(OpenAiChatModel model) {
        this.model = model;
    }

    public String ask(String provider, String prompt) {

        String modelName = switch (provider.toLowerCase()) {
            case "gpt" -> "openai/gpt-oss-20b:free";
            case "claude" -> "anthropic/claude-3-haiku";

            case "gemini" -> "google/gemini-2.5-flash";

            case "llama" -> "meta-llama/llama-3.3-70b-instruct";

            default -> "openai/gpt-oss-20b:free";
        };
    }
}
