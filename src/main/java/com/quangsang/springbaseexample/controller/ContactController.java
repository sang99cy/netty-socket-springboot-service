package com.quangsang.springbaseexample.controller;

import com.quangsang.springbaseexample.dto.response.DataListResponse;
import com.quangsang.springbaseexample.dto.response.GenericResponse;
import com.quangsang.springbaseexample.enums.ErrorCode;
import com.quangsang.springbaseexample.exception.ClientException;
import com.quangsang.springbaseexample.mongodb.model.Contact;
import com.quangsang.springbaseexample.mongodb.repository.ContactRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping(value = "/")
    public DataListResponse<Contact> getAllContacts() {
        return DataListResponse.success(contactRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public GenericResponse<Contact> getContactById(@PathVariable("id") ObjectId id) {
        Contact contact =contactRepository.findBy_id(id);
        return GenericResponse.success(contact);
    }

    @PutMapping(value = "/{id}")
    public void modifyContactById(@PathVariable("id") ObjectId id,@RequestBody Contact contact) {
        contact.set_id(id);
        contactRepository.save(contact);
    }

    @PostMapping(value = "/")
    public GenericResponse<?> createContact(@RequestBody Contact contact) {
        contact.set_id(ObjectId.get());
        return GenericResponse.success(contactRepository.save(contact));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteContact(@PathVariable ObjectId id) {
        contactRepository.delete(contactRepository.findBy_id(id));
    }
}
