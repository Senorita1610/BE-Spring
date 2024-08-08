package com.sportshop.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	private String password;
	private String username;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(String username, String password, Long idUT) {
		this.username = username;
		this.password = password;
		this.authorities = translateAuthorities(idUT);
	}

	private List<GrantedAuthority> translateAuthorities(Long idUT) {
		List<GrantedAuthority> list = new ArrayList<>();
		if (idUT == 1)
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		else if (idUT == 2)
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		else if (idUT == 3)
			list.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		return list;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}