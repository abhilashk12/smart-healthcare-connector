package com.healthcare.backend.authorization.service.impl;

import com.healthcare.backend.authorization.dto.AuthorizationRequestDto;
import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import com.healthcare.backend.authorization.enums.RequestStatus;
import com.healthcare.backend.authorization.repository.AuthorizationRepository;
import com.healthcare.backend.authorization.service.AuthorizationService;
import com.healthcare.backend.patient.entity.Patient;
import com.healthcare.backend.patient.repository.PatientRepository;
import com.healthcare.backend.user.entity.User;
import com.healthcare.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationRepository authorizationRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Override
    public AuthorizationRequest createRequest(AuthorizationRequestDto dto) {

        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found"));
        User provider = userRepository.findById(dto.getProviderId()).orElseThrow(() -> new RuntimeException("Provider not found"));
        User payer = userRepository.findById(dto.getPayerId()).orElseThrow(() -> new RuntimeException("User not found"));

        AuthorizationRequest request = new AuthorizationRequest();

        request.setPatient(patient);
        request.setProvider(provider);
        request.setPayer(payer);

        request.setDiagnosis(dto.getDiagnosis());
        request.setProcedureCode(dto.getProcedureCode());
        request.setDoctorNotes(dto.getDoctorNotes());

        // Every new request starts as DRAFT
        request.setStatus(RequestStatus.DRAFT);

        return authorizationRepository.save(request);
    }

    @Override
    public List<AuthorizationRequest> getAllRequests() {
        return authorizationRepository.findAll();
    }

    @Override
    public AuthorizationRequest getRequestById(Long id) {
        return authorizationRepository.findById(id).orElse(null);
    }

    @Override
    public AuthorizationRequest updateRequest(Long id, AuthorizationRequestDto dto) {

        AuthorizationRequest request = authorizationRepository.findById(id).orElse(null);

        if (request == null) {
            return null;
        }

        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow();
        User provider = userRepository.findById(dto.getProviderId()).orElseThrow();
        User payer = userRepository.findById(dto.getPayerId()).orElseThrow();

        request.setPatient(patient);
        request.setProvider(provider);
        request.setPayer(payer);

        request.setDiagnosis(dto.getDiagnosis());
        request.setProcedureCode(dto.getProcedureCode());
        request.setDoctorNotes(dto.getDoctorNotes());

        return authorizationRepository.save(request);
    }

    @Override
    public void deleteRequest(Long id) {
        authorizationRepository.deleteById(id);
    }

    @Override
    public AuthorizationRequest submit(Long id) {

        AuthorizationRequest request =
                authorizationRepository.findById(id)
                        .orElseThrow();

        request.setStatus(RequestStatus.SUBMITTED);

        return authorizationRepository.save(request);

    }
}