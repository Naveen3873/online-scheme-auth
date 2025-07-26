package com.app.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Must be at least 32 bytes for HS256
    private static final String SECRET = "vickyjwtsupersecuresecretkey1234567890";

    // Convert secret into a Key object (only way to sign in jjwt 0.11.5)
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String mobileNumber) {
        return Jwts.builder()
                .setSubject(mobileNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractMobileNumber(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String mobileNumber = extractMobileNumber(token);
        return mobileNumber.equals(userDetails.getUsername());
    }
}