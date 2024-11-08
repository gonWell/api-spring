package com.samaritano.api.configs.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "chave-mega-hiper-para-entrar-no-samaritano";

    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}