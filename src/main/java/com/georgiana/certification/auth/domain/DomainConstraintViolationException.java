package com.georgiana.certification.auth.domain;

public class DomainConstraintViolationException extends RuntimeException {

    public DomainConstraintViolationException(String message) {
        super(message);
    }
}
