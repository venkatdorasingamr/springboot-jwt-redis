package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.service.JwtTokenService;
import com.example.demo.service.JwtUserDetailsService;

@RestController
public class AuthenticationResource {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenService jwtTokenService;
	
	  @PostMapping("/authenticate")
	    public AuthenticationResponse authenticate(@RequestBody final AuthenticationRequest authenticationRequest) {
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	        } catch (final BadCredentialsException ex) {
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
	        }

	        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
	        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
	        return authenticationResponse;
	    }

}
