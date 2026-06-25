package com.healthcare.backend.patient.service.impl;

import com.healthcare.backend.patient.dto.PatientRequest;
import com.healthcare.backend.patient.entity.Patient;
import com.healthcare.backend.patient.repository.PatientRepository;
import com.healthcare.backend.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient createPatient(PatientRequest request) {

        if (patientRepository.existsByInsuranceNumber(request.getInsuranceNumber())) {
            throw new RuntimeException("Insurance number already exists");
        }

        Patient patient = new Patient();

        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setInsuranceNumber(request.getInsuranceNumber());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setEmail(request.getEmail());

        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient updatePatient(Long id, PatientRequest request) {

        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient == null) {
            return null;
        }

        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setInsuranceNumber(request.getInsuranceNumber());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setEmail(request.getEmail());

        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}