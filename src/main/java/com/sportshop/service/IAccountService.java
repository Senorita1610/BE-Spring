package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.AccountDTO;
import com.sportshop.response.AuthResponse;

public interface IAccountService {
	List<AccountDTO> getAll();

	AccountDTO get(Long id);

	AccountDTO create(AccountDTO accountDTO);

	AccountDTO update(AccountDTO accountDTO);

	boolean delete(Long id);

	AccountDTO loadUserByUsername(String username);

	AuthResponse login(AccountDTO accountDTO);

	AuthResponse signup(AccountDTO accountDTO);

}
