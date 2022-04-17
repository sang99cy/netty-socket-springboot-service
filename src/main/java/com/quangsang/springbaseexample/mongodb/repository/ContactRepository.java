package com.quangsang.springbaseexample.mongodb.repository;

import com.quangsang.springbaseexample.mongodb.model.Contact;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Contact findBy_id(ObjectId _id);
}
