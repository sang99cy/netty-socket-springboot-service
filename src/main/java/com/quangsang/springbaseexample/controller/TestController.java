package com.quangsang.springbaseexample.controller;

import com.quangsang.springbaseexample.client.ContactRestClient;
import com.quangsang.springbaseexample.mongodb.model.Contact;
import com.quangsang.springbaseexample.task.SendAPITask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
/*@CrossOrigin(origins = "*", allowedHeaders = "*")*/
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }

    @PostMapping("/test2")
    public void test2(@RequestBody Contact contact){
        System.out.println("run start ");
        Thread thread = new Thread(new SendAPITask(restTemplate));
        thread.start();
        //ContactRestClient client = new ContactRestClient(restTemplate,"http://localhost",8080);
        //client.saveContact(contact);
    }
}
