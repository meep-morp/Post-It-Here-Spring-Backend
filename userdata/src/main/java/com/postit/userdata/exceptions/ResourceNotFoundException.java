package com.postit.userdata.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super("Error: " + message);
    }
}
