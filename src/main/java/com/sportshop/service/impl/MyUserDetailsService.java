package com.sportshop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sportshop.entity.AccountEntity;
import com.sportshop.repository.AccountRepository;
import com.sportshop.security.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	AccountRepository accountRepo;

	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AccountEntity> userData = accountRepo.findByUsername(username);
		if (userData.isPresent())
			return new MyUserDetails(userData.get().getUsername(), userData.get().getPassword(),
					userData.get().getAccountType().getAccountTypeId());
		throw new UsernameNotFoundException("User not found with username: " + username);
	}

}