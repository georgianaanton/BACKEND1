package com.georgiana.certification.auth.exposition.registration.mentor;

import com.georgiana.certification.auth.domain.Role;
import com.georgiana.certification.auth.domain.security.AddNewAuthority;
import com.georgiana.certification.auth.exposition.CustomRequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@CustomRequestMapping
public class MentorRegistrationResource {
    private final AddNewAuthority addNewAuthority;
    private final RestTemplate restTemplate;

    private final static String PERSONS_SERVICE = "http://persons";


    public MentorRegistrationResource(
            AddNewAuthority addNewAuthority,
            @Autowired @LoadBalanced RestTemplate restTemplate
    ) {
        this.addNewAuthority = addNewAuthority;
        this.restTemplate = restTemplate;
    }

    @PutMapping(value = "/mentors")
    public ResponseEntity<String> registerMentor(@Valid @RequestBody MentorRegistrationCommand registerCommand) throws AuthenticationException {
        registerCommand.role = Role.MENTOR;
        addNewAuthority.addAuthorityFor(registerCommand);
        String responseBody = restTemplate.exchange(
                PERSONS_SERVICE + "/persons/mentors", HttpMethod.PUT,
                new HttpEntity<>(registerCommand), String.class
        ).getBody();
        return ok(responseBody);
    }
}
