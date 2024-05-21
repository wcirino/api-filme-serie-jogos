package com.apifilmeseries.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtTokenFilter extends GenericFilterBean {
	
	 private static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);

	 
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
	    
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = tokenProvider.resolveToken(httpRequest);
	    
	    if (token != null) {
	        log.info("Token resolved: {}", token);
	        if (tokenProvider.validateToken(token)) {
	            log.info("Token validated successfully.");
	            Authentication auth = tokenProvider.getAuthentication(token);
	            if (auth != null) {
	                SecurityContextHolder.getContext().setAuthentication(auth);
	                if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
	                    log.info("User authenticated: {}", auth.getName());
	                } else {
	                    log.warn("Authentication failed for user: {}", auth.getName());
	                }
	            }
	        } else {
	            log.warn("Token validation failed: {}", token);
	        }
	    } else {
	        log.warn("No token found in request.");
	    }
	    
	    chain.doFilter(request, response);
	}

} 