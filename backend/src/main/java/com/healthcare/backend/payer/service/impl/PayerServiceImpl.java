package com.healthcare.backend.payer.service.impl;

import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.authorization.enums.RequestStatus;
import com.healthcare.backend.authorization.repository.AuthorizationRepository;
import com.healthcare.backend.payer.service.PayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayerServiceImpl implements PayerService {

    private final AuthorizationRepository authorizationRepository;

    @Override
    public List<AuthorizationRequest> getPendingRequests() {

        return authorizationRepository.findByStatus(RequestStatus.SUBMITTED);

    }

    @Override
    public AuthorizationRequest approve(Long id) {

        AuthorizationRequest request =
                authorizationRepository.findById(id).orElseThrow();

        request.setStatus(RequestStatus.APPROVED);

        return authorizationRepository.save(request);

    }

    @Override
    public AuthorizationRequest reject(Long id) {

        AuthorizationRequest request =
                authorizationRepository.findById(id).orElseThrow();

        request.setStatus(RequestStatus.REJECTED);

        return authorizationRepository.save(request);

    }

    @Override
    public AuthorizationRequest needMoreInfo(Long id) {

        AuthorizationRequest request =
                authorizationRepository.findById(id).orElseThrow();

        request.setStatus(RequestStatus.NEED_MORE_INFO);

        return authorizationRepository.save(request);

    }

}