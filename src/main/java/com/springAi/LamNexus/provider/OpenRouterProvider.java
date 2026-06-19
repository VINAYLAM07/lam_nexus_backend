package com.springAi.LamNexus.provider;

import com.springAi.LamNexus.util.ResponseCleaner;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class OpenRouterProvider implements ModelProvider{

    private final OpenAiChatModel chatModel;

    public OpenRouterProvider(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String generate(String model, String prompt) {
        Prompt p = new Prompt(prompt, OpenAiChatOptions.builder()
                .model(model)
                .build());
        String response = chatModel.call(p).getResult().getOutput().getText();
        return ResponseCleaner.clean(response);
    }
}
