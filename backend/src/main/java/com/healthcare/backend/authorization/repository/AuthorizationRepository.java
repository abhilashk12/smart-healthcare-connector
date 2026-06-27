package com.healthcare.backend.authorization.repository;

import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.authorization.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorizationRepository extends JpaRepository<AuthorizationRequest, Long> {

    List<AuthorizationRequest> findByStatus(RequestStatus status);
}