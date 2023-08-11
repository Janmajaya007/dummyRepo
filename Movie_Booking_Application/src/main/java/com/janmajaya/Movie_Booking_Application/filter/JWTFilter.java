package com.janmajaya.Movie_Booking_Application.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;

@Component
@Slf4j
public class JWTFilter extends OncePerRequestFilter
{
private final String SECRET="6A576D5A7134743777217A25432A462D4A614E645267556B5870327235753878";
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		HttpServletRequest myRequest=(HttpServletRequest) request;
		HttpServletResponse myResponse=(HttpServletResponse) response;
		
		String header=myRequest.getHeader("Authorization");
		
		if(header==null && !header.startsWith(header)) {
			throw new ServletException("Missing header or the header doesn't starts with bearer");
		}
		
		String token=header.substring(7);

		
		Claims claims= Jwts.parserBuilder()
							.setSigningKey(getSecurityKey(SECRET))
							.build()
							.parseClaimsJws(token)
							.getBody();
		
		myRequest.setAttribute("username", claims);
		log.info("do filter working");
		filterChain.doFilter(request, response);

		
	}
	
	private Key getSecurityKey(String secret) {
		byte[] keyBytes=Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
		
	}

}
