package com.springAi.LamNexus.controllers;

import com.springAi.LamNexus.DTO.ChatRequest;
import com.springAi.LamNexus.services.ChatService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springAi.LamNexus.services.ChatService;
@RestController
@RequestMapping("/api")
//@CrossOrigin(
//        origins = "http://localhost:5173",
//        allowedHeaders = "*",
//        methods = {
//                RequestMethod.GET,
//                RequestMethod.POST,
//                RequestMethod.OPTIONS
//        }
//)
//@CrossOrigin(origins = "*")

public class ChatController {

    //https://docs.spring.io/spring-ai/reference/api/chat/openai-chat.html

    private final ChatService chatService;

    @Value("${spring.ai.openai.api.key:NOT_FOUND}")
    private  String apiKey;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

//    @GetMapping("/{prompt}")
//    public ResponseEntity<String> getAnswer(@PathVariable String prompt) {
//        try {
//            return ResponseEntity.ok(model.call(prompt));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError()
//                    .body(e.getMessage());
//        }
//    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatRequest request) {
        System.out.println("==============");
        System.out.println("Provider: " + request.provider());
        System.out.println("Model: " + request.model());
        System.out.println("Prompt: " + request.prompt());
        try {
            System.out.println(
                    "Provider = " + request.provider());
            return ResponseEntity.ok(
                    chatService.ask(request.provider(),request.model(), request.prompt())
            )
                    ;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }
    @GetMapping("/test")
    public String test() {
        return "Backend OK";
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("API Key: " + apiKey);
//    }

}
