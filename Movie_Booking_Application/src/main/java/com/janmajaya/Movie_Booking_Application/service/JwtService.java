package com.janmajaya.Movie_Booking_Application.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtService {
	
private final String SECRET="6A576D5A7134743777217A25432A462D4A614E645267556B5870327235753878";
	
	public String getRole(String token) {
    	return (String)Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getOrDefault("role", "null");
    }
	
	 private Key getSignKey() {
	        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	 }
	

}
