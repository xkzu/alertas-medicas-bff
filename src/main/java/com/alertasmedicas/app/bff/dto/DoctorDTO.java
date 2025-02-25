package com.alertasmedicas.app.bff.dto;

public record DoctorDTO(
        Long id,
        String name,
        String speciality,
        String email,
        boolean isAdmin
) {}
