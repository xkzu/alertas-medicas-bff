package com.alertasmedicas.app.bff.dto;

public record PatientDTO(
        Long id,
        Long idDoctor,
        String name,
        String state
) {}
