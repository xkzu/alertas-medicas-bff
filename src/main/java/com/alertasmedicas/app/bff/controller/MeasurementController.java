package com.alertasmedicas.app.bff.controller;

import com.alertasmedicas.app.bff.dto.MeasurementDTO;
import com.alertasmedicas.app.bff.service.MeasurementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/getMeasurements/{idPatient}")
    public ResponseEntity<List<MeasurementDTO>> getMeasurements(@PathVariable("idPatient") String idPatient) {
        log.info("Obteniendo las mediciones del paciente por id {}", idPatient);
        try {
            List<MeasurementDTO> measurements = measurementService.getMeasurementsByIdPatient(idPatient);
            log.info("Lista de pacientes obtenida exitosamente por id doctor. Total: {} pacientes.", measurements.size());
            return ResponseEntity.ok(measurements);
        } catch (Exception e) {
            log.error("Error al obtener mediciones del paciente: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<MeasurementDTO> addMeasurement(@RequestBody MeasurementDTO measurementDTO) {
        log.info("Registrando la medicion del paciente: {}", measurementDTO);
        try {
            MeasurementDTO measuremen = measurementService.saveMeasurement(measurementDTO);
            log.info("Medicion registrada exitosamente: {}", measuremen);
            return ResponseEntity.ok(measuremen);
        } catch (Exception e) {
            log.error("Error al registrar la medicion del paciente: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
