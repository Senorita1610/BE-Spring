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

import com.sportshop.dto.AccountTypeDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.service.IAccountTypeService;

@CrossOrigin
@RestController
@RequestMapping("/account-type")
public class AccountTypeController {
	@Autowired
	IAccountTypeService accountTypeService;

	@GetMapping("/all")
	public List<AccountTypeDTO> all() {
		return accountTypeService.getAll();
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody AccountTypeDTO dto) {
		return ResponseDTO.ok(accountTypeService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(accountTypeService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody AccountTypeDTO dto) {
		return ResponseDTO.ok(accountTypeService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(accountTypeService.delete(id));
	}
}
