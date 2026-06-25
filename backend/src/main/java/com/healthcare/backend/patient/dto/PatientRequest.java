package com.healthcare.backend.patient.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientRequest {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String insuranceNumber;

    private String phoneNumber;

    private String email;
}