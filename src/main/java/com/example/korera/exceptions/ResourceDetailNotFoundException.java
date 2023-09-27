package com.example.korera.exceptions;

public class ResourceDetailNotFoundException extends RuntimeException{

    public ResourceDetailNotFoundException(String message){
        super(message);
    }

    public ResourceDetailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
