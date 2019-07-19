package com.georgiana.certification.auth.infra;

import com.georgiana.certification.auth.domain.Role;
import com.georgiana.certification.auth.domain.security.AddNewAuthority;
import com.georgiana.certification.auth.exposition.AuthorityCreateCommand;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Component
@Transactional
public class InitialDataSetRunner implements ApplicationRunner {
    private final AddNewAuthority addNewAuthority;
    public InitialDataSetRunner(AddNewAuthority addNewAuthority) {
        this.addNewAuthority = addNewAuthority;
    }

    @Override
    public void run(ApplicationArguments args) {
        AuthorityCreateCommand georgiana = new AuthorityCreateCommand("georgiana@georgiana.com", Role.USER, "georgiana");
        AuthorityCreateCommand linus = new AuthorityCreateCommand("linus.coolguy@cool.com", Role.MENTOR, "linus");
        AuthorityCreateCommand edward = new AuthorityCreateCommand("edward@edward.com", Role.MENTOR, "edward");
        AuthorityCreateCommand john = new AuthorityCreateCommand("john@john.com", Role.MENTOR, "john");
        AuthorityCreateCommand hercules = new AuthorityCreateCommand("hercules@power.com", Role.MENTOR, "power");
        AuthorityCreateCommand admin = new AuthorityCreateCommand("admin@admin.com", Role.ADMIN, "admin");
        Stream.of(georgiana, linus, edward, john, hercules, admin).forEach(addNewAuthority::addAuthorityFor);
    }
}
