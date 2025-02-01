package com.alertasmedicas.app.bff.service;

import com.alertasmedicas.app.bff.dto.PatientDTO;
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

    @Override
    public List<PatientDTO> getPatientsByIdDoctor(String idDoctor) {
        String url = domain + "/patient/getPatients/" + idDoctor;
        ResponseEntity<List<PatientDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    @Override
    public PatientDTO savePatient(PatientDTO patientDTO) {
        String url = domain + "/patient/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PatientDTO> requestEntity = new HttpEntity<>(patientDTO, headers);
        ResponseEntity<PatientDTO> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                PatientDTO.class
        );
        return response.getBody();
    }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) {
        String url = domain + "/patient/update";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PatientDTO> requestEntity = new HttpEntity<>(patientDTO, headers);
        ResponseEntity<PatientDTO> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                PatientDTO.class
        );
        return response.getBody();
    }
}
