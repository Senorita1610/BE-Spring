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

import com.sportshop.dto.BillDTO;
import com.sportshop.dto.PackageDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.service.IBillService;

@CrossOrigin
@RestController
@RequestMapping("/bill")
public class BillController {
	@Autowired
	IBillService billService;

	@GetMapping("/all")
	public List<BillDTO> all() {
		return billService.getAll();
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody BillDTO dto) {
		return ResponseDTO.ok(billService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(billService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody BillDTO dto) {
		return ResponseDTO.ok(billService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(billService.delete(id));
	}

	@PostMapping("/create-by-customer/{phoneNumber}")
	ResponseDTO<Object> createByCustomer(@PathVariable("phoneNumber") String phoneNumber, @RequestBody PackageDTO dto) {
		return ResponseDTO.ok(billService.createByCustomer(phoneNumber, dto));
	}

}
