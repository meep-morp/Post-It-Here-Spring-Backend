package com.postit.userdata.exceptions;

public class ResourceFoundException extends RuntimeException{
    public ResourceFoundException(String message) {
        super("Error: " + message);
    }
}
