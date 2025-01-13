package com.alertasmedicas.app.bff.controller;

import com.alertasmedicas.app.bff.dto.PatientDTO;
import com.alertasmedicas.app.bff.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/getpatients/{idDoctor}")
    public ResponseEntity<List<PatientDTO>> getPatientById(@PathVariable("idDoctor") String idDoctor) {
        log.info("Obteniendo todos las pacientes por id doctor {}", idDoctor);
        try {
            List<PatientDTO> patients = patientService.getPatientsByIdDoctor(idDoctor);
            log.info("Lista de pacientes obtenida exitosamente por id doctor. Total: {} pacientes.", patients.size());
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            log.error("Error al obtener pacientes por id doctor: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO) {
        log.info("Registrando paciente {}", patientDTO);
        try {
            PatientDTO patient = patientService.savePatient(patientDTO);
            log.info("Paciente registrado exitosamente: {}", patient);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            log.error("Error al registrar paciente: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
        log.info("Actualizando paciente {}", patientDTO);
        try {
            PatientDTO patient = patientService.updatePatient(patientDTO);
            log.info("Paciente actualizado exitosamente: {}", patient);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            log.error("Error al actualizar paciente: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
