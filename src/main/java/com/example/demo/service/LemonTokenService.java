package com.example.demo.service;

import com.nimbusds.jwt.JWTClaimsSet;

import java.util.Map;

public interface LemonTokenService {

	String LEMON_IAT = "lemon-iat";

	String createToken(String aud, String subject, Long expirationMillis, Map<String, Object> claimMap);
	String createToken(String audience, String subject, Long expirationMillis);
	JWTClaimsSet parseToken(String token, String audience);
	JWTClaimsSet parseToken(String token, String audience, long issuedAfter);
	<T> T parseClaim(String token, String claim);
}