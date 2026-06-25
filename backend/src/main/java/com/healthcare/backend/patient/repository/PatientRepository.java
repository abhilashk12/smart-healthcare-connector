package com.healthcare.backend.patient.repository;

import com.healthcare.backend.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    boolean existsByInsuranceNumber(String insuranceNumber);

}