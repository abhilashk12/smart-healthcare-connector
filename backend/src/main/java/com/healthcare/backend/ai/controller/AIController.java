package com.healthcare.backend.ai.controller;

import com.healthcare.backend.ai.dto.AIReviewResponse;
import com.healthcare.backend.ai.service.AIService;
import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.authorization.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/review/{id}")
    public AIReviewResponse review(@PathVariable Long id){

        return aiService.review(id);

    }

}