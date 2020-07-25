package com.postit.userdata.services;

import com.postit.userdata.models.User;
import com.postit.userdata.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userrepos;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userrepos.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }
}
