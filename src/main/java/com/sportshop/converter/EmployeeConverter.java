package com.sportshop.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportshop.dto.EmployeeDTO;
import com.sportshop.entity.EmployeeEntity;

@Component
public class EmployeeConverter {
	@Autowired
	AccountConverter accountConverter;

	public EmployeeEntity toEntity(EmployeeDTO dto) {
		EmployeeEntity en = new EmployeeEntity();
		en.setEmployeeId(dto.getEmployeeId());
		en.setAddress(dto.getAddress());
		en.setBirthday(dto.getBirthday());
		en.setGender(dto.getGender());
		en.setJoinDate(dto.getJoinDate());
		en.setName(dto.getName());
		en.setPhoneNumber(dto.getPhoneNumber());
		en.setAccount(accountConverter.toEntity(dto.getAccount()));
		return en;
	}

	public EmployeeDTO toDTO(EmployeeEntity en) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeId(en.getEmployeeId());
		dto.setAddress(en.getAddress());
		dto.setBirthday(en.getBirthday());
		dto.setGender(en.getGender());
		dto.setJoinDate(en.getJoinDate());
		dto.setName(en.getName());
		dto.setPhoneNumber(en.getPhoneNumber());
		dto.setAccount(accountConverter.toDTO(en.getAccount()));
		return dto;
	}
}
