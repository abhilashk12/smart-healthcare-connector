package com.healthcare.backend.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    @NotBlank
    private String insuranceNumber;

    private String phoneNumber;

//    @Email
    private String email;
}