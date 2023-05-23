/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this artifact or file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.BlueTokenService;
import com.example.demo.service.LemonPrincipal;
import com.example.demo.service.UserDto;
import com.example.demo.util.LecUtils;
import com.nimbusds.jwt.JWTClaimsSet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Filter for token authentication
 */
@AllArgsConstructor
@Slf4j
public class LemonCommonsWebTokenAuthenticationFilter extends OncePerRequestFilter {
	
    private final BlueTokenService blueTokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.debug("Inside LemonTokenAuthenticationFilter ...");
		
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);				
		
    	if (header != null && header.startsWith(LecUtils.TOKEN_PREFIX)) { // token present
			
			log.debug("Found a token");			
		    String token = header.substring(7);
		    
		    try {
		    	
		    	Authentication auth = createAuthToken(token);
		    	SecurityContextHolder.getContext().setAuthentication(auth);
		    	
				log.debug("Token authentication successful");
				    		    	
		    } catch (Exception e) {
		    	
				log.debug("Token authentication failed - {}", e.getMessage());
				
		    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Authentication Failed: " + e.getMessage());
		    	
		    	return;
		    }
		    
		} else
		
			log.debug("Token authentication skipped");
		
		filterChain.doFilter(request, response);
	}

	protected Authentication createAuthToken(String token) {
		
		JWTClaimsSet claims = blueTokenService.parseToken(token, BlueTokenService.AUTH_AUDIENCE);
		UserDto userDto = LecUtils.getUserDto(claims);
		if (userDto == null)
			userDto = fetchUserDto(claims);
		
        LemonPrincipal principal = new LemonPrincipal(userDto);
        		
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
	}

	/*
	 * Default behaviour is to throw error. To be overridden in auth service.
	 */
	protected UserDto fetchUserDto(JWTClaimsSet claims) {
		throw new AuthenticationCredentialsNotFoundException("com.naturalprogrammer.spring.userClaimAbsent");
	}
}
