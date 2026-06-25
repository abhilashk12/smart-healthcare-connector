package com.healthcare.backend.authorization.entity;

import com.healthcare.backend.authorization.enums.RequestStatus;
import com.healthcare.backend.common.entity.BaseEntity;
import com.healthcare.backend.patient.entity.Patient;
import com.healthcare.backend.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorization_requests")
public class AuthorizationRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Patient for whom authorization is requested
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Hospital/Doctor
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;

    // Insurance Company
    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private User payer;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String procedureCode;

    @Column(columnDefinition = "TEXT")
    private String doctorNotes;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}