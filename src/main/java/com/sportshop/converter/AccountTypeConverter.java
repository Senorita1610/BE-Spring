package com.sportshop.converter;

import org.springframework.stereotype.Component;

import com.sportshop.dto.AccountTypeDTO;
import com.sportshop.entity.AccountTypeEntity;

@Component
public class AccountTypeConverter {
	public AccountTypeEntity toEntity(AccountTypeDTO dto) {
		AccountTypeEntity en = new AccountTypeEntity();
		en.setAccountTypeId(dto.getAccountTypeId());
		en.setName(dto.getName());
		en.setPosition(dto.getPosition());
		en.setRole(dto.getRole());
		return en;
	}

	public AccountTypeDTO toDTO(AccountTypeEntity en) {
		AccountTypeDTO dto = new AccountTypeDTO();
		dto.setAccountTypeId(en.getAccountTypeId());
		dto.setName(en.getName());
		dto.setPosition(en.getPosition());
		dto.setRole(en.getRole());
		return dto;
	}
}
