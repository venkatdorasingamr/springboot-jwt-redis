package com.example.demo.controller;

public class AuthenticationResource {
	
	/*
	 * @Autowired private JwtUserDetailsService jwtUserDetailsService;
	 * 
	 * @Autowired private AuthenticationManager authenticationManager;
	 * 
	 * @Autowired private JwtTokenService jwtTokenService;
	 * 
	 * @PostMapping("/authenticate") public AuthenticationResponse
	 * authenticate(@RequestBody final AuthenticationRequest authenticationRequest)
	 * { try { authenticationManager.authenticate(new
	 * UsernamePasswordAuthenticationToken( authenticationRequest.getUsername(),
	 * authenticationRequest.getPassword())); } catch (final BadCredentialsException
	 * ex) { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED); }
	 * 
	 * final UserDetails userDetails =
	 * jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername())
	 * ; final AuthenticationResponse authenticationResponse = new
	 * AuthenticationResponse();
	 * authenticationResponse.setAccessToken(jwtTokenService.generateToken(
	 * userDetails)); return authenticationResponse; }
	 */
}
