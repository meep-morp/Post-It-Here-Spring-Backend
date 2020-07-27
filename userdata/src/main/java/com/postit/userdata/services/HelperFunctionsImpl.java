package com.postit.userdata.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions{
    @Override
    public boolean isAuthorizedToMakeChange(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return username.equalsIgnoreCase(authentication.getName().toLowerCase()) || authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public String getCurrentAuditor() {
        String uname;
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        if (authUser != null) {
            uname = authUser.getName();
        } else {
            uname = "SYSTEM";
        }

        return uname;
    }
}
