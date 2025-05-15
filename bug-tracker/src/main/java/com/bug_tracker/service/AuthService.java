package com.bug_tracker.service;


import com.bug_tracker.exception.NotAuthenticatedException;
import com.bug_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    public String varify(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(username);
        }
        else {
            throw new NotAuthenticatedException("user not authenticated");
        }
    }
}
