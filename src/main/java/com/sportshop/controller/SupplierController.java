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

import com.sportshop.dto.ResponseDTO;
import com.sportshop.dto.SupplierDTO;
import com.sportshop.service.ISupplierService;

@CrossOrigin
@RestController
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	ISupplierService supplierService;

	@GetMapping("/all")
	public List<SupplierDTO> all() {
		return supplierService.getAll();
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody SupplierDTO dto) {
		return ResponseDTO.ok(supplierService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(supplierService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody SupplierDTO dto) {
		return ResponseDTO.ok(supplierService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(supplierService.delete(id));
	}

}
