package com.georgiana.certification.auth.exposition.authority;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.georgiana.certification.auth.domain.Role;
import com.georgiana.certification.auth.domain.security.Authority;

import lombok.Data;

@Data
public class AuthorityDto {
    @JsonProperty private String email;
    @JsonProperty private Role role;
    @JsonProperty private boolean isNotLocked;

    public AuthorityDto(Authority authority) {
        this.email = authority.getId().getValue();
        this.role = authority.getRole();
        this.isNotLocked = authority.isAccountNonLocked();
    }
}
