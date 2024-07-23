package com.kosign.bill24.exception;

public class NotFoundExceptionClass extends RuntimeException{
    public NotFoundExceptionClass(String message){
        super(message);
    }
}