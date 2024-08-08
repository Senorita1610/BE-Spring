package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.CustomerDTO;

public interface ICustomerService {
	List<CustomerDTO> getAll();

	CustomerDTO get(Long id);

	CustomerDTO create(CustomerDTO customerDTO);

	CustomerDTO update(CustomerDTO customerDTO);

	boolean delete(Long id);
}
