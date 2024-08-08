package com.sportshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportshop.dto.EmployeeDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.service.IEmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	IEmployeeService employeeService;

	@GetMapping("/all")
	public List<EmployeeDTO> all() {
		return employeeService.getAll();
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody EmployeeDTO dto) {
		return ResponseDTO.ok(employeeService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(employeeService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody EmployeeDTO dto) {
		return ResponseDTO.ok(employeeService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(employeeService.delete(id));
	}
}
