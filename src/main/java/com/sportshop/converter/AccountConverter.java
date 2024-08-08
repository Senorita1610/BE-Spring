package com.sportshop.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportshop.dto.AccountDTO;
import com.sportshop.entity.AccountEntity;

@Component
public class AccountConverter {
	@Autowired
	AccountTypeConverter accountTypeConverter;

	public AccountEntity toEntity(AccountDTO dto) {
		AccountEntity en = new AccountEntity();
		en.setAccountId(dto.getAccountId());
		en.setFullName(dto.getFullName());
		en.setUsername(dto.getUsername());
		en.setPassword(dto.getPassword());
		en.setAccountType(accountTypeConverter.toEntity(dto.getAccountType()));
		return en;
	}

	public AccountDTO toDTO(AccountEntity en) {
		AccountDTO dto = new AccountDTO();
		dto.setAccountId(en.getAccountId());
		dto.setFullName(en.getFullName());
		dto.setUsername(en.getUsername());
		dto.setPassword(en.getPassword());
		dto.setAccountType(accountTypeConverter.toDTO(en.getAccountType()));
		return dto;
	}
}
