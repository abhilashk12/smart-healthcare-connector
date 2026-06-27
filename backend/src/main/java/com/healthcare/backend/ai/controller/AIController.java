package com.healthcare.backend.ai.controller;

import com.healthcare.backend.ai.dto.AIReviewResponse;
import com.healthcare.backend.ai.service.AIService;
import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.authorization.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;
    private final ChatClient chatClient;


    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/review/{id}")
    public AIReviewResponse review(@PathVariable Long id){

        return aiService.review(id);

    }


    //Sample API for checking Ollama is working or not.
    @GetMapping
    public String test() {

        return chatClient
                .prompt("Say Hello")
                .call()
                .content();
    }

}