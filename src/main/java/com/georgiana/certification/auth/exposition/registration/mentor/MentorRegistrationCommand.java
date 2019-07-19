package com.georgiana.certification.auth.exposition.registration.mentor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.georgiana.certification.auth.exposition.AuthorityCreateCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorRegistrationCommand extends AuthorityCreateCommand {
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String phoneNumber;
    @JsonProperty
    private int yearsOfExperience;
    @JsonProperty
    private String linkedinUrl;


}
