package com.example.demo.service;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.util.UserUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A lighter User class,
 * mainly used for holding logged-in user data 
 */
@Getter @Setter @ToString
public class UserDto implements Serializable {

	private static final long serialVersionUID = -9134054705405149534L;
	
	private String id;
	private String username;
	private String password;
	private Set<String> roles = new HashSet<>();
	private Serializable tag;
	
	private boolean unverified = false;
	private boolean blocked = false;
	private boolean admin = false;
	private boolean goodUser = false;
	private boolean goodAdmin = false;
	
	public void initialize() {
		
		unverified = roles.contains(UserUtils.Role.UNVERIFIED);
		blocked = roles.contains(UserUtils.Role.BLOCKED);
		admin = roles.contains(UserUtils.Role.ADMIN);
		goodUser = !(unverified || blocked);
		goodAdmin = goodUser && admin;
	}
}