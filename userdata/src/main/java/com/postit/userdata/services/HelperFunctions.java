package com.postit.userdata.services;

public interface HelperFunctions {
    boolean isAuthorizedToMakeChange(String username);

    String getCurrentAuditor();
}
