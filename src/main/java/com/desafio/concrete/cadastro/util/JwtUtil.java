package com.desafio.concrete.cadastro.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	static final String SECRET = "MySecreteApp";
	static final String TOKEN_PREFIX = "Bearer";
	
	public static String generateToken(String login) {
        Claims claims = Jwts.claims().setSubject(login);
        
        String jwt = Jwts.builder()
		        .setClaims(claims)
		        .signWith(SignatureAlgorithm.HS512, SECRET)
		        .compact();

        return TOKEN_PREFIX + " " + jwt;
    }
	
	public static String parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            return body.getSubject();
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }
	
}
