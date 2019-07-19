package com.georgiana.certification.auth.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.georgiana.certification.auth.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityCreateCommand {
    @JsonProperty
    @NotEmpty
    public String username;
    @JsonProperty
    public Role role;
    @JsonProperty
    @NotEmpty
    public String password;
}
