package com.healthcare.backend.payer.service;

import com.healthcare.backend.authorization.entity.AuthorizationRequest;

import java.util.List;

public interface PayerService {

    List<AuthorizationRequest> getPendingRequests();

    AuthorizationRequest approve(Long id);

    AuthorizationRequest reject(Long id);

    AuthorizationRequest needMoreInfo(Long id);

}