package com.alertasmedicas.app.bff.service;

import com.alertasmedicas.app.bff.dto.MeasurementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final RestTemplate restTemplate;

    @Value("${api.measurement:}")
    private String domain;

    @Autowired
    public MeasurementServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MeasurementDTO> getMeasurementsByIdPatient(String idPatient) {
        String url = domain + "/measurement/getMeasurements/" + idPatient;
        ResponseEntity<List<MeasurementDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    @Override
    public MeasurementDTO saveMeasurement(MeasurementDTO measurementDTO) {
        String url = domain + "/measurement/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MeasurementDTO> requestEntity = new HttpEntity<>(measurementDTO, headers);
        ResponseEntity<MeasurementDTO> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                MeasurementDTO.class
        );
        return response.getBody();
    }
}
