package org.mql.ai.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;

import java.util.Set;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential upc) {
            String username = upc.getCaller();
            String password = upc.getPasswordAsString();

            if ("user".equals(username) && "password".equals(password)) {
                return new CredentialValidationResult("user", Set.of("USER"));
            }
        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}
