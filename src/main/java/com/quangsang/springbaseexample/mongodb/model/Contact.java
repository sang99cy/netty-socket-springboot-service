package com.quangsang.springbaseexample.mongodb.model;

/*import com.quangsang.springbaseexample.audit.Auditable;*/
import org.bson.types.ObjectId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Document(collection = "contact")
/*@EntityListeners(AuditingEntityListener.class)*/
public class Contact /*extends Auditable<String>*/ {
    @Id
    private ObjectId _id;

    private String name;
    private Integer age;
    private String email;

    public Contact() {
    }

    public Contact(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Contact(ObjectId _id, String name, Integer age, String email) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
