package com.alertasmedicas.app.bff.controller;

import com.alertasmedicas.app.bff.dto.DoctorDTO;
import com.alertasmedicas.app.bff.service.DoctorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        log.info("Obteniendo todos las doctores...");
        try {
        List<DoctorDTO> doctors = doctorService.getDoctors();
            log.info("Lista de doctores obtenida exitosamente. Total: {} pacientes.", doctors.size());
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            log.error("Error al obtener doctores: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
