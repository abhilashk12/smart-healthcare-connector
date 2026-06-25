package com.healthcare.backend.auth.service.impl;

import com.healthcare.backend.auth.dto.AuthResponse;
import com.healthcare.backend.auth.dto.LoginRequest;
import com.healthcare.backend.auth.dto.RegisterRequest;
import com.healthcare.backend.auth.service.AuthService;
import com.healthcare.backend.user.entity.User;
import com.healthcare.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email already exists.");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        userRepository.save(user);

        return new AuthResponse("User registered successfully.");
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return new AuthResponse("Invalid email or password.");
        }

        // Compare raw password with encrypted password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Invalid email or password.");
        }

        return new AuthResponse("Login successful.");
    }
}