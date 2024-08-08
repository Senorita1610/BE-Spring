package com.sportshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sportshop.converter.AccountConverter;
import com.sportshop.dto.AccountDTO;
import com.sportshop.entity.AccountEntity;
import com.sportshop.entity.AccountTypeEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.AccountRepository;
import com.sportshop.response.AuthResponse;
import com.sportshop.service.IAccountService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;

@Service
public class AccountService implements IAccountService {
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	AccountConverter accountConverter;

	@Override
	public List<AccountDTO> getAll() {
		List<AccountEntity> list = accountRepo.findAll();
		List<AccountDTO> result = new ArrayList<>();
		for (AccountEntity en : list) {
			AccountDTO dto = accountConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public AccountDTO get(Long id) {
		AccountEntity en = accountRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("account.not.exists", "id", id));
		});
		AccountDTO dto = accountConverter.toDTO(en);
		return dto;
	}

	@Override
	public AccountDTO create(AccountDTO request) {
		String fullName = request.getFullName();
		String username = request.getUsername();
		String password = request.getPassword();
		InputValidateUtil.validateNotNull("fullName", fullName);
		InputValidateUtil.validateNotNull("username", username);
		InputValidateUtil.validateNotNull("password", password);
		accountRepo.findByUsername(username).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("account.exists", "username", username));
		});

		AccountEntity account = accountConverter.toEntity(request);
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String hash = bcrypt.encode(password);
		account.setPassword(hash);
		AccountDTO dto = accountConverter.toDTO(accountRepo.save(account));
		return dto;
	}

	@Override
	public AccountDTO update(AccountDTO request) {
		Long accountId = request.getAccountId();
		String fullName = request.getFullName();
		InputValidateUtil.validateNotNull("fullName", fullName);
		AccountEntity account = accountRepo.findById(accountId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("account.not.exists","id",accountId));
		});
		AccountEntity en = accountConverter.toEntity(request);
		account.setFullName(en.getFullName());
		account.setAccountType(en.getAccountType());
		AccountDTO dto = accountConverter.toDTO(accountRepo.save(account));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		accountRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("account.not.exists", "id", id));
		});
		accountRepo.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public AccountDTO loadUserByUsername(String username) {
		Optional<AccountEntity> en = accountRepo.findByUsername(username);
		if (en.isPresent()) {
			return accountConverter.toDTO(en.get());
		}
		return null;
	}

	@Override
	public AuthResponse login(AccountDTO accountDTO) {
		/// Check user name
		Optional<AccountEntity> userData = accountRepo.findByUsername(accountDTO.getUsername());
		if (userData.isPresent()) {
			// Check password
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			AccountEntity entity = userData.get();
			if (bcrypt.matches(accountDTO.getPassword(), entity.getPassword())) {
				return new AuthResponse(200, "Login is successful");
			}
			return new AuthResponse(401, "Password is wrong");
		}
		return new AuthResponse(401, "Email is not existed");
	}

	@Override
	public AuthResponse signup(AccountDTO accountDTO) {
		// Check user name is existed
		Optional<AccountEntity> userData = accountRepo.findByUsername(accountDTO.getUsername());
		if (userData.isPresent()) {
			return new AuthResponse(400, "This username is existed");
		} else {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String hash = bcrypt.encode(accountDTO.getPassword());
			AccountEntity entity = accountConverter.toEntity(accountDTO);
			entity.setPassword(hash);
			entity.setAccountType(new AccountTypeEntity(1L));
			accountRepo.save(entity);
			return new AuthResponse(200, "Signup is successful");
		}
	}
}
