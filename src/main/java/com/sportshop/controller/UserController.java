package com.sportshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportshop.config.JwtTokenUtils;
import com.sportshop.dto.UserDTO;
import com.sportshop.response.AuthResponse;
import com.sportshop.security.MyUserDetails;
import com.sportshop.service.IUserService;
import com.sportshop.service.impl.MyUserDetailsService;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	IUserService userService;
	@Autowired
	MyUserDetailsService userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtils jwtTokenUtils;

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UserDTO> getAll() {
		return userService.getAll();
	}

//	@PostMapping("/login")
//	public ResponseEntity<AuthResponse> login(@RequestBody UserDTO userDTO) throws Exception {
//		_authenticate(userDTO.getEmail(), userDTO.getPassword());
//		final MyUserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
//		String token = jwtTokenUtils.generateToken(userDetails);
//		UserDTO userInfo = userService.loadUserByEmail(userDTO.getEmail());
//		return ResponseEntity.ok(new AuthResponse(200, "Login is successful", userInfo, token));
//	}
//
//	@PostMapping("/authenticate")
//	public ResponseEntity<AuthResponse> authenticate(Authentication authentication) throws Exception {
//		String email = authentication.getName();
//		UserDTO userInfo = userService.loadUserByEmail(email);
//		return ResponseEntity.ok(new AuthResponse(200, "Authenticated", userInfo));
//	}
//
//	@PutMapping("signup")
//	public ResponseEntity<AuthResponse> signup(@RequestBody UserDTO userDTO) {
//		AuthResponse r = userService.signup(userDTO);
//		if (r.status == 200) {
//			return ResponseEntity.ok(r);
//		} else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
//		}
//	}

	private void _authenticate(String email, String password) throws Exception {
		try {
			// Load user details by email
			MyUserDetails userDetails = userDetailsService.loadUserByUsername(email);

			// Authenticate using email and password
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities()));
		} catch (AuthenticationException e) {
			// Authentication failed
			throw new Exception("Invalid credentials", e);
		}
	}
}
