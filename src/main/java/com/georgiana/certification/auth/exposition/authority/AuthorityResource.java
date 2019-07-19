package com.georgiana.certification.auth.exposition.authority;

import com.georgiana.certification.auth.domain.EmailAddress;
import com.georgiana.certification.auth.domain.security.Authorities;
import com.georgiana.certification.auth.domain.security.Authority;
import com.georgiana.certification.auth.domain.security.RoleConstants;
import com.georgiana.certification.auth.exposition.CustomRequestMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.RolesAllowed;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@CustomRequestMapping
public class AuthorityResource {

    private Authorities authorities;

    public AuthorityResource(Authorities authorities) {
        this.authorities = authorities;
    }

    @GetMapping(value = "/authorities")
    public Set<AuthorityDto> getAuthorities() throws AuthenticationException {
        return authorities.findAll().stream()
                .map(AuthorityDto::new)
                .collect(toSet());
    }

    @PostMapping("/authorities/{accountAddress}/lock")
    @RolesAllowed({RoleConstants.ADMIN})
    public ResponseEntity<Boolean> lockUserAccount(@PathVariable EmailAddress accountAddress) {
        Authority userAuthority = authorities.findById(accountAddress);
        userAuthority.lockUser();
        return ResponseEntity.ok(true);
    }

    @PostMapping("/authorities/{accountAddress}/unlock")
    @RolesAllowed({RoleConstants.ADMIN})
    public ResponseEntity<Boolean> unlockUserAccount(@PathVariable EmailAddress accountAddress) {
        Authority userAuthority = authorities.findById(accountAddress);
        userAuthority.unlockUser();
        return ResponseEntity.ok(true);
    }

}
