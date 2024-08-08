package com.sportshop.converter;

import org.springframework.stereotype.Component;

import com.sportshop.dto.UserDTO;
import com.sportshop.entity.UserEntity;

@Component
public class UserConverter {
	public UserEntity toEntity(UserDTO dto) {
		UserEntity en = new UserEntity();
		en.setAddress(dto.getAddress());
		en.setEmail(dto.getEmail());
		en.setId(dto.getId());
		en.setUserStatus(dto.getUserStatus());
		en.setUserType(dto.getUserType());
		en.setNameUser(en.getNameUser());
		en.setPassword(dto.getPassword());
		en.setRegtime(dto.getRegtime());
		en.setSalt(dto.getSalt());
		en.setVerify(dto.getVerify());
		return en;
	}

	public UserDTO toDTO(UserEntity en) {
		UserDTO dto = new UserDTO();
		dto.setAddress(en.getAddress());
		dto.setEmail(en.getEmail());
		dto.setId(en.getId());
		dto.setUserStatus(en.getUserStatus());
		dto.setUserType(en.getUserType());
		dto.setNameUser(en.getNameUser());
		dto.setPassword(en.getPassword());
		dto.setPhone(en.getPhone());
		dto.setRegtime(en.getRegtime());
		dto.setSalt(en.getSalt());
		dto.setVerify(en.getVerify());
		return dto;
	}
}
