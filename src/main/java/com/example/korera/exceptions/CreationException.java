package com.example.korera.exceptions;

public class CreationException extends RuntimeException{

    public CreationException(String m){
        super(m);
    }

    public CreationException(String m, Throwable throwable){
        super(m,throwable);
    }
}
