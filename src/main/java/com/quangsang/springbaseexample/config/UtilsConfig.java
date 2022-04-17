/*
package com.quangsang.springbaseexample.config;

import com.quangsang.springbaseexample.mongodb.model.ERole;
import com.quangsang.springbaseexample.mongodb.model.Role;
import com.quangsang.springbaseexample.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class UtilsConfig {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void RoleInit() {
            Role user = new Role(ERole.ROLE_USER);
            Role admin = new Role(ERole.ROLE_ADMIN);
            Role moderator = new Role(ERole.ROLE_MODERATOR);
            roleRepository.save(user);
            roleRepository.save(admin);
            roleRepository.save(moderator);
    }
}
*/
