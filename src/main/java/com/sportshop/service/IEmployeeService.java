package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.EmployeeDTO;

public interface IEmployeeService {
	List<EmployeeDTO> getAll();

	EmployeeDTO get(Long id);

	EmployeeDTO create(EmployeeDTO employeeDTO);

	EmployeeDTO update(EmployeeDTO employeeDTO);

	boolean delete(Long id);
}
