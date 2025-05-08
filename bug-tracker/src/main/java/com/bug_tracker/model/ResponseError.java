package com.bug_tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseError {
    private LocalDateTime timeStamp;
    private String message;
    private int status;
}
