package com.alertasmedicas.app.bff.service;

import com.alertasmedicas.app.bff.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

    List<DoctorDTO> getDoctors();
}
