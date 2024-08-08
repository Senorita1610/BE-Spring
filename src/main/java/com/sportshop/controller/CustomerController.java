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

import com.sportshop.dto.CustomerDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.service.ICustomerService;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	ICustomerService customerService;

	@GetMapping("/all")
	public List<CustomerDTO> all() {
		return customerService.getAll();
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody CustomerDTO dto) {
		return ResponseDTO.ok(customerService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(customerService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody CustomerDTO dto) {
		return ResponseDTO.ok(customerService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(customerService.delete(id));
	}

}
