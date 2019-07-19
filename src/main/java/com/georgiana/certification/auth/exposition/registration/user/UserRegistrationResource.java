package com.georgiana.certification.auth.exposition.registration.user;

import com.georgiana.certification.auth.domain.Role;
import com.georgiana.certification.auth.domain.security.AddNewAuthority;
import com.georgiana.certification.auth.exposition.CustomRequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@CustomRequestMapping
public class UserRegistrationResource {
    private final AddNewAuthority addNewAuthority;
    private final RestTemplate restTemplate;

    private final static String PERSONS_SERVICE = "http://persons";


    public UserRegistrationResource(
            AddNewAuthority addNewAuthority,
            @Autowired @LoadBalanced RestTemplate restTemplate
    ) {
        this.addNewAuthority = addNewAuthority;
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/users")
    public String registerUser(@Valid @RequestBody UserCreateCommand registerCommand) throws AuthenticationException {
        registerCommand.role = Role.USER;
        addNewAuthority.addAuthorityFor(registerCommand);
        registerCommand.setPhoneNumber("0723796007");
        return restTemplate.exchange(
                PERSONS_SERVICE + "/persons/users", HttpMethod.POST,
                new HttpEntity<>(registerCommand), String.class
        ).getBody();
    }
}
