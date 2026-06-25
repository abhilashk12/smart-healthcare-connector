package com.healthcare.backend.authorization.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationRequestDto {

    private Long patientId;

    private Long providerId;

    private Long payerId;

    private String diagnosis;

    private String procedureCode;

    private String doctorNotes;
}