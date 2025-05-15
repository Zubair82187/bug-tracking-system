package com.bug_tracker.exception;


public class NotAuthenticatedException extends RuntimeException{
    public NotAuthenticatedException(String message){
        super(message);
    }
}
