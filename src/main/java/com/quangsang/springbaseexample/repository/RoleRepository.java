package com.quangsang.springbaseexample.repository;

import com.quangsang.springbaseexample.mongodb.model.ERole;
import com.quangsang.springbaseexample.mongodb.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
