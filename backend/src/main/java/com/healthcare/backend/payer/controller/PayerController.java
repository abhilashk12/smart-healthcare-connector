package com.healthcare.backend.payer.controller;

import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.payer.service.PayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payer")
@RequiredArgsConstructor
public class PayerController {

    private final PayerService payerService;

    @GetMapping("/pending")
    public List<AuthorizationRequest> pending() {
        return payerService.getPendingRequests();
    }

    @PutMapping("/{id}/approve")
    public AuthorizationRequest approve(@PathVariable Long id) {
        return payerService.approve(id);
    }

    @PutMapping("/{id}/reject")
    public AuthorizationRequest reject(@PathVariable Long id) {
        return payerService.reject(id);
    }

    @PutMapping("/{id}/need-more-info")
    public AuthorizationRequest needMoreInfo(@PathVariable Long id) {
        return payerService.needMoreInfo(id);
    }
}