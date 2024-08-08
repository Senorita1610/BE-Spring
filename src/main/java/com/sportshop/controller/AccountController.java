package com.sportshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportshop.config.JwtTokenUtils;
import com.sportshop.dto.AccountDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.response.AuthResponse;
import com.sportshop.security.MyUserDetails;
import com.sportshop.service.IAccountService;
import com.sportshop.service.impl.MyUserDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	IAccountService accountService;
	@Autowired
	MyUserDetailsService userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtils jwtTokenUtils;

	@GetMapping("/all")
	public List<AccountDTO> all() {
		return accountService.getAll();
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AccountDTO accountDTO) throws Exception {
		_authenticate(accountDTO.getUsername(), accountDTO.getPassword());
		final MyUserDetails userDetails = userDetailsService.loadUserByUsername(accountDTO.getUsername());
		String token = jwtTokenUtils.generateToken(userDetails);
		AccountDTO userInfo = accountService.loadUserByUsername(accountDTO.getUsername());
		return ResponseEntity.ok(new AuthResponse(200, "Login is successful", userInfo, token));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(Authentication authentication) throws Exception {
		String username = authentication.getName();
		AccountDTO userInfo = accountService.loadUserByUsername(username);
		return ResponseEntity.ok(new AuthResponse(200, "Authenticated", userInfo));
	}

	@PutMapping("signup")
	public ResponseEntity<AuthResponse> signup(@RequestBody AccountDTO accountDTO) {
		AuthResponse r = accountService.signup(accountDTO);
		if (r.status == 200) {
			return ResponseEntity.ok(r);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
	}

	private void _authenticate(String username, String password) throws Exception {
		try {
			// Load user details by email
			MyUserDetails userDetails = userDetailsService.loadUserByUsername(username);

			// Authenticate using email and password
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities()));
		} catch (AuthenticationException e) {
			// Authentication failed
			throw new Exception("Invalid credentials", e);
		}
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody AccountDTO dto) {
		return ResponseDTO.ok(accountService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(accountService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody AccountDTO dto) {
		return ResponseDTO.ok(accountService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(accountService.delete(id));
	}

}
