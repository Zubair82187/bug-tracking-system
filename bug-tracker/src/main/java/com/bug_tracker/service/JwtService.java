package com.bug_tracker.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.io.Decoders;


public class JwtService {

    private final String SECRET = "2620ffc6b5804ae05a5755f305510c29a2e1f510d961f60088aa7036b218e6b0";

    public String extractUserName(String token) {
        return null;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return true;
    }


}
