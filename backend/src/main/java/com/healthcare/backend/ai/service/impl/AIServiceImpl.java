package com.healthcare.backend.ai.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.backend.ai.dto.AIReviewResponse;
import com.healthcare.backend.ai.prompt.HealthcarePromptBuilder;
import com.healthcare.backend.ai.service.AIService;
import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.authorization.enums.RequestStatus;
import com.healthcare.backend.authorization.repository.AuthorizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final AuthorizationRepository authorizationRepository;
    private final ChatClient chatClient;
    private final HealthcarePromptBuilder promptBuilder;
    private final ObjectMapper objectMapper;

    @Override
    public AIReviewResponse review(Long authorizationId) {

        AuthorizationRequest request = authorizationRepository
                .findById(authorizationId)
                .orElseThrow(() -> new RuntimeException("Authorization Request not found"));

        // ==========================
        // Step 1 : Java Validation
        // ==========================

        List<String> validationErrors = getStrings(request);

        // Stop here if mandatory fields are missing
        if (!validationErrors.isEmpty()) {

            return new AIReviewResponse(
                    "Authorization request is incomplete.",
                    100,
                    "INCOMPLETE",
                    validationErrors
            );
        }

        // ==========================
        // Step 2 : AI Review
        // ==========================

        String prompt = promptBuilder.buildPrompt(request);

        String response = chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

        System.out.println("========= RAW AI RESPONSE =========");
        System.out.println(response);

        String cleaned = response
                .replaceFirst("^```json\\s*", "")  // Remove opening ```json
                .replaceFirst("^```\\s*", "")      // Remove opening ``` (if present)
                .replaceFirst("\\s*```\\s*$", "")  // Remove closing ```
                .trim();

        System.out.println("========= RAW AI RESPONSE =========");
        System.out.println(cleaned);
        try {

            AIReviewResponse aiReview =
                    objectMapper.readValue(cleaned, AIReviewResponse.class);

            if ("READY".equalsIgnoreCase(aiReview.getOverallStatus())) {
                request.setStatus(RequestStatus.AI_REVIEWED);
            } else {
                request.setStatus(RequestStatus.DRAFT);
            }

            authorizationRepository.save(request);

            return aiReview;

        } catch (JsonProcessingException e) {

            e.printStackTrace();

            AIReviewResponse fallback = new AIReviewResponse();

            fallback.setSummary("AI returned an invalid response.");
            fallback.setConfidence(0);
            fallback.setOverallStatus("INCOMPLETE");
            fallback.setSuggestions(
                    List.of("Please review the authorization request manually.")
            );

            return fallback;
        }
    }

    private static List<String> getStrings(AuthorizationRequest request) {
        List<String> validationErrors = new ArrayList<>();

        if (request.getPatient() == null) {
            validationErrors.add("Patient information is missing.");
        }

        if (request.getProvider() == null) {
            validationErrors.add("Provider information is missing.");
        }

        if (request.getPayer() == null) {
            validationErrors.add("Payer information is missing.");
        }

        if (request.getDiagnosis() == null || request.getDiagnosis().isBlank()) {
            validationErrors.add("Diagnosis is required.");
        }

        if (request.getProcedureCode() == null || request.getProcedureCode().isBlank()) {
            validationErrors.add("Procedure code is required.");
        }

        if (request.getDoctorNotes() == null || request.getDoctorNotes().isBlank()) {
            validationErrors.add("Doctor notes are required.");
        }
        return validationErrors;
    }
}