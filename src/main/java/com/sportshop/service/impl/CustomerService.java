package com.sportshop.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.CustomerConverter;
import com.sportshop.dto.CustomerDTO;
import com.sportshop.entity.CustomerEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.CustomerRepository;
import com.sportshop.service.ICustomerService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	CustomerConverter customerConverter;

	@Override
	public List<CustomerDTO> getAll() {
		List<CustomerEntity> list = customerRepo.findAll();
		List<CustomerDTO> result = new ArrayList<>();
		for (CustomerEntity en : list) {
			CustomerDTO dto = customerConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public CustomerDTO get(Long id) {
		CustomerEntity en = customerRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("customer.not.exists", "id", id));
		});
		CustomerDTO dto = customerConverter.toDTO(en);
		return dto;
	}

	@Override
	public CustomerDTO create(CustomerDTO request) {
		String address = request.getAddress();
		LocalDate birthday = request.getBirthday();
		Integer gender = request.getGender();
		String name = request.getName();
		String phoneNumber = request.getPhoneNumber();
		Integer usagePeriod = request.getUsagePeriod();
		InputValidateUtil.validateNotNull("address", address);
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("phoneNumber", phoneNumber);
		customerRepo.findByPhoneNumber(phoneNumber).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("customer.exists", "phone number", phoneNumber));
		});
		CustomerEntity customer = customerConverter.toEntity(request);
		CustomerDTO dto = customerConverter.toDTO(customerRepo.save(customer));
		return dto;
	}

	@Override
	public CustomerDTO update(CustomerDTO request) {
		Long customerId = request.getCustomerId();
		String address = request.getAddress();
		LocalDate birthday = request.getBirthday();
		Integer gender = request.getGender();
		String name = request.getName();
		String phoneNumber = request.getPhoneNumber();
		Integer usagePeriod = request.getUsagePeriod();
		InputValidateUtil.validateNotNull("address", address);
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("phoneNumber", phoneNumber);
		CustomerEntity customer = customerRepo.findById(customerId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("customer.not.exists", "id", customerId));
		});
		
		if (!customer.getPhoneNumber().equals(phoneNumber)) {
	        customerRepo.findByPhoneNumber(phoneNumber).ifPresent(u -> {
	            throw new ValidateCommonException(MsgUtil.getMessage("customer.exists", "phone number", phoneNumber));
	        });
	    }
		
		CustomerEntity en = customerConverter.toEntity(request);
		customer.setAddress(en.getAddress());
		customer.setBirthday(en.getBirthday());
		customer.setGender(en.getGender());
		customer.setName(en.getName());
		customer.setPhoneNumber(en.getPhoneNumber());
		customer.setUsagePeriod(en.getUsagePeriod());
		customer.setAccount(en.getAccount());
		CustomerDTO dto = customerConverter.toDTO(customerRepo.save(customer));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		customerRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("customer.not.exists", "id", id));
		});
		customerRepo.deleteById(id);
		return Boolean.TRUE;
	}

}
