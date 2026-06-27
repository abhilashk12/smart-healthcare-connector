package com.healthcare.backend.authorization.service;

import com.healthcare.backend.authorization.dto.AuthorizationRequestDto;
import com.healthcare.backend.authorization.entity.AuthorizationRequest;

import java.util.List;

public interface AuthorizationService {

    AuthorizationRequest createRequest(AuthorizationRequestDto dto);

    List<AuthorizationRequest> getAllRequests();

    AuthorizationRequest getRequestById(Long id);

    AuthorizationRequest updateRequest(Long id, AuthorizationRequestDto dto);

    void deleteRequest(Long id);

    AuthorizationRequest submit(Long id);
}