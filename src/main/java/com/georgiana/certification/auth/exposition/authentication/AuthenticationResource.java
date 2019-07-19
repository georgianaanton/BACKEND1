package com.georgiana.certification.auth.exposition.authentication;


import com.georgiana.certification.auth.domain.security.Authority;
import com.georgiana.certification.auth.exposition.CustomRequestMapping;
import com.georgiana.certification.auth.infra.security.TokenProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CustomRequestMapping
public class AuthenticationResource {
    private AuthenticationManager authenticationManager;
    private TokenProvider jwtTokenUtil;

    public AuthenticationResource(AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/token/generate-token")
    public AuthentifiedUserDto login(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Authority loggedAuthority = (Authority) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(authentication);
        return new AuthentifiedUserDto(loggedAuthority, token);
    }
}
