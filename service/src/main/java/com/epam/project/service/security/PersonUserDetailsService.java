package com.epam.project.service.security;

import com.epam.project.entity.Person;
import com.epam.project.entity.Role;
import com.epam.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  * A Spring Security UserDetailsService implementation which creates UserDetails
 * objects from the Person and Role entities.
 *
 * Created by Aliaksandr_Khmurchyk on 14-Feb-18.
 */
@Service
public class PersonUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Person person = personService.findByMail(email);
        if (person == null) {
            throw new UsernameNotFoundException(
                    "Person with email " + email + " not found.");
        }

        if (person.getRole() == null) {
            throw new UsernameNotFoundException("Person not authorized.");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Role role = person.getRole();

        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        User userDetails = new User(person.getEmail(),
                person.getPassword(), true,
                true, true,
                true, grantedAuthorities);

        return userDetails;
    }
}
