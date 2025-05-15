package com.bug_tracker.controller;

import com.bug_tracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        return service.varify(username, password);
    }



}
