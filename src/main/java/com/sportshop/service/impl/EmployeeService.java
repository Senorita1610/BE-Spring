package com.sportshop.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.EmployeeConverter;
import com.sportshop.dto.EmployeeDTO;
import com.sportshop.entity.EmployeeEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.EmployeeRepository;
import com.sportshop.service.IEmployeeService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	EmployeeConverter employeeConverter;

	@Override
	public List<EmployeeDTO> getAll() {
		List<EmployeeEntity> list = employeeRepo.findAll();
		List<EmployeeDTO> result = new ArrayList<>();
		for (EmployeeEntity en : list) {
			EmployeeDTO dto = employeeConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public EmployeeDTO get(Long id) {
		EmployeeEntity en = employeeRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("employee.not.exists", "id", id));
		});
		EmployeeDTO dto = employeeConverter.toDTO(en);
		return dto;
	}

	@Override
	public EmployeeDTO create(EmployeeDTO request) {
		String address = request.getAddress();
		LocalDate birthday = request.getBirthday();
		Integer gender = request.getGender();
		String name = request.getName();
		String phoneNumber = request.getPhoneNumber();
		LocalDate joinDate = request.getJoinDate();
		InputValidateUtil.validateNotNull("address", address);
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("phoneNumber", phoneNumber);
		employeeRepo.findByPhoneNumber(phoneNumber).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("employee.exists", "phone number", phoneNumber));
		});
		EmployeeEntity employee = employeeConverter.toEntity(request);
		EmployeeDTO dto = employeeConverter.toDTO(employeeRepo.save(employee));
		return dto;
	}

	@Override
	public EmployeeDTO update(EmployeeDTO request) {
		Long employeeId = request.getEmployeeId();
		String address = request.getAddress();
		LocalDate birthday = request.getBirthday();
		Integer gender = request.getGender();
		String name = request.getName();
		String phoneNumber = request.getPhoneNumber();
		LocalDate joinDate = request.getJoinDate();
		InputValidateUtil.validateNotNull("address", address);
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("phoneNumber", phoneNumber);
		EmployeeEntity employee = employeeRepo.findById(employeeId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("employee.not.exists", "id", employeeId));
		});
		if(!employee.getPhoneNumber().equals(phoneNumber)){
			employeeRepo.findByPhoneNumber(phoneNumber).ifPresent(u -> {
				throw new ValidateCommonException(MsgUtil.getMessage("employee.exists", "phone number", phoneNumber));
			});
		}
		EmployeeEntity en = employeeConverter.toEntity(request);
		employee.setAddress(en.getAddress());
		employee.setBirthday(en.getBirthday());
		employee.setGender(en.getGender());
		employee.setName(en.getName());
		employee.setPhoneNumber(en.getPhoneNumber());
		employee.setJoinDate(en.getJoinDate());
		employee.setAccount(en.getAccount());
		EmployeeDTO dto = employeeConverter.toDTO(employeeRepo.save(employee));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		employeeRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("employee.not.exists", "id", id));
		});
		employeeRepo.deleteById(id);
		return Boolean.TRUE;
	}

}
