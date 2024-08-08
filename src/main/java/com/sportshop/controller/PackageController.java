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

import com.sportshop.dto.PackageDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.service.IPackageService;

@CrossOrigin
@RestController
@RequestMapping("/package")
public class PackageController {
	@Autowired
	IPackageService packageService;

	@GetMapping("/all")
	public List<PackageDTO> all() {
		return packageService.getAll();
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody PackageDTO dto) {
		return ResponseDTO.ok(packageService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(packageService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody PackageDTO dto) {
		return ResponseDTO.ok(packageService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(packageService.delete(id));
	}

	@GetMapping("/find/{slug}")
	ResponseDTO<Object> getBySlug(@PathVariable String slug) {
		return ResponseDTO.ok(packageService.getBySlug(slug));
	}

}
