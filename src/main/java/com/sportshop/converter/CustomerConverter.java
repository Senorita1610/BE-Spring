package com.sportshop.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportshop.dto.CustomerDTO;
import com.sportshop.entity.CustomerEntity;

@Component
public class CustomerConverter {
	@Autowired
	AccountConverter accountConverter;

	public CustomerEntity toEntity(CustomerDTO dto) {
		CustomerEntity en = new CustomerEntity();
		en.setCustomerId(dto.getCustomerId());
		en.setAddress(dto.getAddress());
		en.setBirthday(dto.getBirthday());
		en.setGender(dto.getGender());
		en.setName(dto.getName());
		en.setPhoneNumber(dto.getPhoneNumber());
		en.setUsagePeriod(dto.getUsagePeriod());
		en.setAccount(accountConverter.toEntity(dto.getAccount()));
		return en;
	}

	public CustomerDTO toDTO(CustomerEntity en) {
		CustomerDTO dto = new CustomerDTO();
		dto.setCustomerId(en.getCustomerId());
		dto.setAddress(en.getAddress());
		dto.setBirthday(en.getBirthday());
		dto.setGender(en.getGender());
		dto.setName(en.getName());
		dto.setPhoneNumber(en.getPhoneNumber());
		dto.setUsagePeriod(en.getUsagePeriod());
		dto.setAccount(accountConverter.toDTO(en.getAccount()));
		return dto;
	}
}
