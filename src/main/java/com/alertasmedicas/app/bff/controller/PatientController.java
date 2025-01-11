package com.alertasmedicas.app.bff.controller;

import com.alertasmedicas.app.bff.dto.PatientDTO;
import com.alertasmedicas.app.bff.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        log.info("Obteniendo todos las pacientes...");
        try {
        List<PatientDTO> patients = patientService.getPatients();
            log.info("Lista de pacientes obtenida exitosamente. Total: {} pacientes.", patients.size());
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            log.error("Error al obtener pacientes: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
