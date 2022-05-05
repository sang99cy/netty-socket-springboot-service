//package com.quangsang.springbaseexample.client;
//
//import com.quangsang.springbaseexample.model.Employee;
//import com.quangsang.springbaseexample.mongodb.model.Contact;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Objects;
//
//public class ContactRestClient {
//    private static final String RESOURCE_PATH = "/api/contacts/";
//
//    private Logger LOG = LoggerFactory.getLogger(EmployeeRestClient.class);
//    private String REQUEST_URI;
//    private RestTemplate restTemplate;
//
//    public ContactRestClient(RestTemplate restTemplate, String host, int port) {
//        this.restTemplate = restTemplate;
//        this.REQUEST_URI = host + ":" + port + RESOURCE_PATH;
//    }
//
//    public Contact saveContact(Contact contact) {
//        return restTemplate.postForObject(REQUEST_URI + "/", contact, Contact.class);
//    }
//}
