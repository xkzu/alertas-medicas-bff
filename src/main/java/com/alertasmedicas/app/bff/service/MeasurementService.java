package com.alertasmedicas.app.bff.service;

import com.alertasmedicas.app.bff.dto.MeasurementDTO;

import java.util.List;

public interface MeasurementService {
    List<MeasurementDTO> getMeasurementsByIdPatient(String idPatient);

    MeasurementDTO saveMeasurement(MeasurementDTO measurementDTO);
}
