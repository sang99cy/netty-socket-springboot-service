package com.quangsang.springbaseexample.controller;

import com.quangsang.springbaseexample.client.InfluxDBRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test1")
    public ResponseEntity<Object> test1(){
        InfluxDBRestClient client = new InfluxDBRestClient(restTemplate);
        ResponseEntity<Object> response = client.getAllBuckets("Y-QllqlPdEeqtKIbo3D2guXb7ObPGTwDN3NKhkGN6rIXc_Q4VxKJtxkInp6ENBErF70cgnVAG8iIlVX9L9Ss4Q==");
        return response;
    }
}
