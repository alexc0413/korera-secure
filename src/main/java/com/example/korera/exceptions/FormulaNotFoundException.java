package com.example.korera.exceptions;

public class FormulaNotFoundException extends RuntimeException{

    public FormulaNotFoundException(String message){
        super(message);
    }

    public FormulaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
