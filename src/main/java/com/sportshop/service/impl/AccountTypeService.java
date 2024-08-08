package com.sportshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.AccountTypeConverter;
import com.sportshop.dto.AccountTypeDTO;
import com.sportshop.entity.AccountTypeEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.AccountTypeRepository;
import com.sportshop.service.IAccountTypeService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;

@Service
public class AccountTypeService implements IAccountTypeService {
	@Autowired
	AccountTypeRepository accountTypeRepo;
	@Autowired
	AccountTypeConverter accountTypeConverter;

	@Override
	public List<AccountTypeDTO> getAll() {
		List<AccountTypeEntity> list = accountTypeRepo.findAll();
		List<AccountTypeDTO> result = new ArrayList<>();
		for (AccountTypeEntity en : list) {
			AccountTypeDTO dto = accountTypeConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public AccountTypeDTO get(Long id) {
		AccountTypeEntity en = accountTypeRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("accounttype.not.exists", "id", id));
		});
		AccountTypeDTO dto = accountTypeConverter.toDTO(en);
		return dto;
	}

	@Override
	public AccountTypeDTO create(AccountTypeDTO request) {
		String name = request.getName();
		String position = request.getPosition();
		String role = request.getRole();
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("position", position);
		InputValidateUtil.validateNotNull("role", role);
		accountTypeRepo.findByRole(role).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("accounttype.exists", "role", role));
		});
		AccountTypeEntity accountType = accountTypeConverter.toEntity(request);
		AccountTypeDTO dto = accountTypeConverter.toDTO(accountTypeRepo.save(accountType));
		return dto;
	}

	@Override
	public AccountTypeDTO update(AccountTypeDTO request) {
		Long accountTypeId = request.getAccountTypeId();
		String name = request.getName();
		String position = request.getPosition();
		String role = request.getRole();
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("position", position);
		InputValidateUtil.validateNotNull("role", role);
		accountTypeRepo.findByRole(role).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("accounttype.exists", "role", role));
		});
		AccountTypeEntity accountType = accountTypeRepo.findById(accountTypeId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("accounttype.not.exists", "id", accountTypeId));
		});
		AccountTypeEntity en = accountTypeConverter.toEntity(request);
		accountType.setName(en.getName());
		accountType.setPosition(en.getPosition());
		accountType.setRole(en.getRole());
		AccountTypeDTO dto = accountTypeConverter.toDTO(accountTypeRepo.save(accountType));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		accountTypeRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("accounttype.not.exists", "id", id));
		});
		accountTypeRepo.deleteById(id);
		return Boolean.TRUE;
	}

}
