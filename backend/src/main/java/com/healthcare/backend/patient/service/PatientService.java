package com.healthcare.backend.patient.service;

import com.healthcare.backend.patient.dto.PatientRequest;
import com.healthcare.backend.patient.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient createPatient(PatientRequest request);

    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient updatePatient(Long id, PatientRequest request);

    void deletePatient(Long id);

}