package com.bug_tracker.exception;

public class DeletionFailedException extends RuntimeException{
    public DeletionFailedException(String message){
        super(message);
    }
}
