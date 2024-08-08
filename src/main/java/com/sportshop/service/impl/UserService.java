package com.sportshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sportshop.converter.UserConverter;
import com.sportshop.dto.UserDTO;
import com.sportshop.entity.UserEntity;
import com.sportshop.entity.UserStatusEntity;
import com.sportshop.entity.UserTypeEntity;
import com.sportshop.repository.UserRepository;
import com.sportshop.response.AuthResponse;
import com.sportshop.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserConverter userConverter;

	@Override
	public List<UserDTO> getAll() {
		List<UserEntity> list = userRepo.findAll();
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		for (UserEntity en : list) {
			UserDTO dto = userConverter.toDTO(en);
			listDTO.add(dto);
		}
		return listDTO;
	}

	@Override
	public UserDTO loadUserByEmail(String email) {
		Optional<UserEntity> en=userRepo.findByEmail(email);
		if(en.isPresent()) {
			return userConverter.toDTO(en.get());
		}
		return null;
	}
	@Override
	public AuthResponse login(UserDTO userDTO) {
		// Check email
		Optional<UserEntity> userData = userRepo.findByEmail(userDTO.getEmail());
		if (userData.isPresent()) {
			// Check password
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			UserEntity entity = userData.get();
			if (bcrypt.matches(userDTO.getPassword(), entity.getPassword())) {
				return new AuthResponse(200, "Login is successful");
			}
			return new AuthResponse(401, "Password is wrong");
		}
		return new AuthResponse(401, "Email is not existed");
	}

	@Override
	public AuthResponse signup(UserDTO userDTO) {
		// Check email is existed
		Optional<UserEntity> userData = userRepo.findByEmail(userDTO.getEmail());
		if (userData.isPresent()) {
			return new AuthResponse(400, "This email is existed");
		} else {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String hash = bcrypt.encode(userDTO.getPassword());
			UserEntity entity = userConverter.toEntity(userDTO);
			entity.setPassword(hash);
			entity.setUserStatus(new UserStatusEntity(1L));
			entity.setUserType(new UserTypeEntity(1L));
			userRepo.save(entity);
			return new AuthResponse(200, "Signup is successful");
		}
	}

}
