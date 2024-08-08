package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.UserDTO;
import com.sportshop.response.AuthResponse;

public interface IUserService {
	List<UserDTO> getAll();

	UserDTO loadUserByEmail(String email);

	AuthResponse login(UserDTO userDTO);

	AuthResponse signup(UserDTO userDTO);
}
