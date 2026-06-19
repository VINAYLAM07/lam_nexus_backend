package com.springAi.LamNexus.provider;

import com.springAi.LamNexus.util.ResponseCleaner;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;


@Service
public class OllamaProvider implements ModelProvider {

    private final OllamaChatModel chatModel;

    public OllamaProvider(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String generate(String model, String prompt) {
        Prompt p = new Prompt(prompt, OllamaChatOptions.builder().model(model).temperature(0.7).numPredict(300).build());
        String response = chatModel.call(p)
                .getResult()
                .getOutput()
                .getText();
        return ResponseCleaner.clean(response);
    }
}
