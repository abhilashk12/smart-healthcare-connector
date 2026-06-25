package com.healthcare.backend.auth.service;

import com.healthcare.backend.auth.dto.AuthResponse;
import com.healthcare.backend.auth.dto.LoginRequest;
import com.healthcare.backend.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}