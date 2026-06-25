package com.healthcare.backend.authorization.repository;

import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<AuthorizationRequest, Long> {

}