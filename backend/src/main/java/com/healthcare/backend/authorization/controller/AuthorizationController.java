package com.healthcare.backend.authorization.controller;

import com.healthcare.backend.authorization.dto.AuthorizationRequestDto;
import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.authorization.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authorizations")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping
    public AuthorizationRequest create(@RequestBody AuthorizationRequestDto dto) {
        return authorizationService.createRequest(dto);
    }

    @GetMapping
    public List<AuthorizationRequest> getAll() {
        return authorizationService.getAllRequests();
    }

    @GetMapping("/{id}")
    public AuthorizationRequest getById(@PathVariable Long id) {
        return authorizationService.getRequestById(id);
    }

    @PutMapping("/{id}")
    public AuthorizationRequest update(@PathVariable Long id,
                                       @RequestBody AuthorizationRequestDto dto) {
        return authorizationService.updateRequest(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorizationService.deleteRequest(id);
    }

    @PostMapping("/{id}/submit")
    public AuthorizationRequest submit(@PathVariable Long id) {

        return authorizationService.submit(id);

    }
}