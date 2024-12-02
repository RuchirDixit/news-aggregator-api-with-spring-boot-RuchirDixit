package com.airtribe.newsaggregatorapp.news_aggregator_app.config;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

// Class to generate JWT token and extract username
@Component
public class JWTUtil {

    private final String SECRET_KEY = System.getenv("SECRET_KEY");

    /**
     * Generate JWT token on basis of username
     * @param username
     * @return
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    /**
     * Check if token is expired on basis of current date
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}

