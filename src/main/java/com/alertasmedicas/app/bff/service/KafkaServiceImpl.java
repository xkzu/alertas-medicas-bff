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
public class KafkaServiceImpl implements KafkaService {

    private final RestTemplate restTemplate;

    @Value("${api.kafka.consumer:}")
    private String domain;

    @Autowired
    public KafkaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MeasurementDTO> getMeasurements() {
        String uri = domain + "/kafka/measurements";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MeasurementDTO> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<MeasurementDTO>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }
}
