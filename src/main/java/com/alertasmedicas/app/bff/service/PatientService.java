package com.alertasmedicas.app.bff.service;

import com.alertasmedicas.app.bff.dto.PatientDTO;

import java.util.List;

public interface PatientService {

    List<PatientDTO> getPatients();

    List<PatientDTO> getPatientsByIdDoctor(String idDoctor);

    PatientDTO savePatient(PatientDTO patientDTO);

    PatientDTO updatePatient(PatientDTO patientDTO);
}
