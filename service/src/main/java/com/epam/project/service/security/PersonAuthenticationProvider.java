package com.epam.project.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * A Spring Security AuthenticationProvider which extends
 * <code>AbstractUserDetailsAuthenticationProvider</code>. This classes uses the
 * <code>PersonUserDetailsService</code> to retrieve a UserDetails instance.
 *
 * A PasswordEncoder compares the supplied authentication credentials to those
 * in the UserDetails.
 *
 * Created by Aliaksandr_Khmurchyk on 14-Feb-18.
 */
@Component
public class PersonAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private PersonUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {

        if (token.getCredentials() == null
                || userDetails.getPassword() == null) {
            throw new BadCredentialsException("Credentials may not be null.");
        }

//        if (!passwordEncoder.matches((String) token.getCredentials(),
//                userDetails.getPassword())) {
//            throw new BadCredentialsException("Invalid credentials.");
//        }
    }

    @Override
    protected UserDetails retrieveUser(String email,
                                       UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return userDetails;
    }
}
