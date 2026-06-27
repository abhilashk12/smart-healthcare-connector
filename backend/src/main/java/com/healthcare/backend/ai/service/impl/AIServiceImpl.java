package com.healthcare.backend.ai.service.impl;

import com.healthcare.backend.ai.dto.AIReviewResponse;
import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.ai.service.AIService;
import com.healthcare.backend.authorization.enums.RequestStatus;
import com.healthcare.backend.authorization.repository.AuthorizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final AuthorizationRepository authorizationRepository;

    @Override
    public AIReviewResponse review(Long authorizationId) {

        AuthorizationRequest request =
                authorizationRepository.findById(authorizationId)
                        .orElseThrow();

        List<String> suggestions = new ArrayList<>();

        if(request.getDiagnosis()==null || request.getDiagnosis().isBlank())
            suggestions.add("Diagnosis is missing.");

        if(request.getProcedureCode()==null || request.getProcedureCode().isBlank())
            suggestions.add("Procedure code is missing.");

        if(request.getDoctorNotes()==null || request.getDoctorNotes().length()<20)
            suggestions.add("Doctor notes should be more descriptive.");

        request.setStatus(RequestStatus.AI_REVIEWED);

        authorizationRepository.save(request);

        return new AIReviewResponse(
                suggestions,
                90
        );

    }



}