package com.example.demo.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final Integer id;

    public JwtUserDetails(final Integer integer, final String username, final String hash,
                          final Collection<? extends GrantedAuthority> authorities) {
        super(username, hash, authorities);
        this.id = integer;
    }

}