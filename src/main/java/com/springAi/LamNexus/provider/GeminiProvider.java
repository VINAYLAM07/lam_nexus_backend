package com.springAi.LamNexus.provider;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class GeminiProvider implements ModelProvider {

    private final GoogleGenAiChatModel chatModel;

    public GeminiProvider(
            GoogleGenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String generate(
            String model,
            String prompt) {

        return chatModel.call(
                        new Prompt(prompt))
                .getResult()
                .getOutput()
                .getText();
    }
}