package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.AccountTypeDTO;

public interface IAccountTypeService {
	List<AccountTypeDTO> getAll();

	AccountTypeDTO get(Long id);

	AccountTypeDTO create(AccountTypeDTO accountTypeDTO);

	AccountTypeDTO update(AccountTypeDTO accountTypeDTO);

	boolean delete(Long id);

}
