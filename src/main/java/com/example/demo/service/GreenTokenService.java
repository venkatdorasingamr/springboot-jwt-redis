package com.example.demo.service;

public interface GreenTokenService extends LemonTokenService {

	String VERIFY_AUDIENCE = "verify";
	String FORGOT_PASSWORD_AUDIENCE = "forgot-password";
	String CHANGE_EMAIL_AUDIENCE = "change-email";
}