package com.quangsang.springbaseexample.audit;

import com.quangsang.springbaseexample.model.User;
import com.quangsang.springbaseexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    // ------------------ Use below code for spring security --------------------------

    /*class SpringSecurityAuditorAware implements AuditorAware<User> {

        public User getCurrentAuditor() {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return null;
            }

            return ((MyUserDetails) authentication.getPrincipal()).getUser();
        }
    }*/
}
