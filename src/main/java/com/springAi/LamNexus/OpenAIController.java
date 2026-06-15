package com.springAi.LamNexus;

import com.springAi.LamNexus.DTO.ChatRequest;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

public class OpenAIController {

    //https://docs.spring.io/spring-ai/reference/api/chat/openai-chat.html
    private final OpenAiChatModel model;

    //to tell spring to create object
    public OpenAIController(OpenAiChatModel model) {
        this.model = model;
    }

    @GetMapping("/{prompt}")
    public ResponseEntity<String> getAnswer(@PathVariable String prompt) {
        // Here you would implement the logic to call the OpenAI API and get a response based on the prompt.
        // For demonstration purposes, we'll return a dummy response.

        try {
            return ResponseEntity.ok(model.call(prompt));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatRequest request) {
        try {
//            return ResponseEntity.ok(ChatService.ask(
//                            request.provider(),
//                            request.prompt()
//                    )
            System.out.println(
                    "Provider = " + request.provider());
            return ResponseEntity.ok(
                    model.call(request.prompt())
            )
                    ;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }

}
