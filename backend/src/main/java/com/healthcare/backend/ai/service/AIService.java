package com.healthcare.backend.ai.service;

import com.healthcare.backend.ai.dto.AIReviewResponse;
import com.healthcare.backend.authorization.entity.AuthorizationRequest;

public interface AIService {

    AIReviewResponse review(Long authorizationId);


}
