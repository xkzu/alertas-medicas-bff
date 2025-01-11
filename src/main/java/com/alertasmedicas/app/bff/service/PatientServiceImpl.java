package com.alertasmedicas.app.bff.service;

import com.alertasmedicas.app.bff.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final RestTemplate restTemplate;

    @Value("${api.patient:}")
    private String domain;

    @Autowired
    public PatientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PatientDTO> getPatients() {
        String url = domain + "/patient/getPatients";
        ResponseEntity<List<PatientDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
