package com.alertasmedicas.app.bff.service;

import com.alertasmedicas.app.bff.dto.DoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final RestTemplate restTemplate;

    @Value("${api.doctors:}")
    private String domain;

    @Autowired
    public DoctorServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<DoctorDTO> getDoctors() {
        String url = domain + "/doctor/getDoctors";
        ResponseEntity<List<DoctorDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
