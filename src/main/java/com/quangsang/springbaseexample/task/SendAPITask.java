package com.quangsang.springbaseexample.task;

import com.quangsang.springbaseexample.client.ContactRestClient;
import com.quangsang.springbaseexample.mongodb.model.Contact;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SendAPITask implements Runnable {

    final Lock lock = new ReentrantLock();
    private String host = "http://localhost";
    private int port = 8080;
    private static ContactRestClient client;
    private final RestTemplate restTemplate;

    public SendAPITask(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        client = new ContactRestClient(restTemplate, host, port);
    }

    @Override
    public void run() {
        lock.lock();
        try {
            Random random = new Random();
            Contact contact;
            for(int i = 0 ; i < 100000;i++){
                contact = new Contact("khuat quang sang " + random.nextInt(), 23, "quangsang99hn@gmail.com");
                client.saveContact(contact);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
