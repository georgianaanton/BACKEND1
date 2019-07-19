package com.georgiana.certification.auth.exposition.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.georgiana.certification.auth.domain.Role;
import com.georgiana.certification.auth.domain.security.Authority;

import lombok.Data;

@Data
public class AuthentifiedUserDto {
    @JsonProperty
    private String token;
    @JsonProperty
    private String username;
    @JsonProperty
    private Role role;


    public AuthentifiedUserDto(String token) {
        this.token = token;
    }
    public AuthentifiedUserDto(Authority authority, String token) {
        this.token = token;
        this.username = authority.getUsername();
        this.role = authority.getRole();
    }
}
