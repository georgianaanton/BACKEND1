package com.georgiana.certification.auth.infra.persistence;

import com.georgiana.certification.auth.domain.EmailAddress;
import com.georgiana.certification.auth.domain.security.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesSdj extends JpaRepository<Authority, EmailAddress> {
}
