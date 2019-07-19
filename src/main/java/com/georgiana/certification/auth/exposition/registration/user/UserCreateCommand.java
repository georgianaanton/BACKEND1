package com.georgiana.certification.auth.exposition.registration.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.georgiana.certification.auth.exposition.AuthorityCreateCommand;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateCommand extends AuthorityCreateCommand {
    @JsonProperty public String firstName;
    @JsonProperty public String lastName;
    @JsonProperty public String phoneNumber;
}
