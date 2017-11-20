package com.codeup.kidsync.services;

import com.codeup.kidsync.models.User;
import com.codeup.kidsync.models.UserWithRoles;
import com.codeup.kidsync.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
@Service
public class UserDetailsLoader implements UserDetailsService {

    private final UsersRepository repository;

    @Autowired
    public UserDetailsLoader(UsersRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + email);
        }
        return new UserWithRoles(user, Collections.emptyList());
    }
}

