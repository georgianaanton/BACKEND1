package com.georgiana.certification.auth.domain.security;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.georgiana.certification.auth.domain.EmailAddress;

import java.util.Set;

public interface Authorities extends UserDetailsService {
    Authority add(Authority authority);
    Set<Authority> findAll();
    boolean exists(EmailAddress address);
    Authority findById(EmailAddress address);
}
