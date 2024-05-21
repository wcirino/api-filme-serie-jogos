package com.apifilmeseries.jwt;


import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apifilmeseries.dto.TokenDTO;
import com.apifilmeseries.exception.InvalidjwtAuthenticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import io.jsonwebtoken.JwtException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {
	
	 private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
	 
	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	
//	@Value("${security.jwt.token.expire-length:3600000}")
//	private long validityInMilliseconds = 3600000; //1h
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	Algorithm algorithm = null;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}
	

	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodeToken(token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private DecodedJWT decodeToken(String token) {
		Algorithm alg =  Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
			//return bearerToken.substring("Bearer ".length());
		}		
		return null;
	}
		
	public boolean validateToken(String token) {
	    try {
	        DecodedJWT decodedJWT = decodeToken(token);
	        if (decodedJWT.getExpiresAt().before(new Date())) {
	            log.warn("Token expired: {}", token);
	            return false;
	        }
	        log.info("Token validated successfully: {}", token);
	        return true;
	    } catch (JwtException | IllegalArgumentException e) {
	        log.error("Failed to validate token: {}", token, e);
	        throw new InvalidjwtAuthenticationException("Expired or invalid JWT token");
	    }
	}

	
}
