package com.bug_tracker.exception;

public class NotUpdatedException extends RuntimeException{
    public NotUpdatedException(String message){
        super(message);
    }
}
