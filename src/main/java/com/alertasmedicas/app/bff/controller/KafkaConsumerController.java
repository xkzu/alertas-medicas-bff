package com.alertasmedicas.app.bff.controller;

import com.alertasmedicas.app.bff.dto.MeasurementDTO;
import com.alertasmedicas.app.bff.service.KafkaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/kafka")
public class KafkaConsumerController {

    private final KafkaService kafkaService;

    @Autowired
    public KafkaConsumerController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping("/measurements")
    public ResponseEntity<List<MeasurementDTO>> measurements() {
        try {
            log.info("Obteniendo mediciones");
            return ResponseEntity.ok(kafkaService.getMeasurements());
        } catch (Exception e) {
            log.info("Error al obtener anomalias desde Kafka");
            return ResponseEntity.internalServerError().build();
        }
    }

}
