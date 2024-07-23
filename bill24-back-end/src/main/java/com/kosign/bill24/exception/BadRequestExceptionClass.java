package com.kosign.bill24.exception;

public class BadRequestExceptionClass extends RuntimeException{
    public BadRequestExceptionClass(String message){
        super(message);
    }
}
