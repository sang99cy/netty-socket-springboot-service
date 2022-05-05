package com.quangsang.springbaseexample.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class InfluxDBRestClient {

    private Logger LOG = LoggerFactory.getLogger(EmployeeRestClient.class);
    private RestTemplate restTemplate;

    public InfluxDBRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> getAllBuckets(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token " + token);
        HttpEntity<String> entity = new HttpEntity(headers);
        ResponseEntity<Object> response = restTemplate.exchange("http://localhost:8086/api/v2/buckets", HttpMethod.GET, entity,Object.class);
        return response;
    }
}
