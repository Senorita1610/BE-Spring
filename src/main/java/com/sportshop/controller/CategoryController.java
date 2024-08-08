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

import com.sportshop.dto.CategoryDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.service.ICategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	ICategoryService categoryService;

	@GetMapping("/all")
	public List<CategoryDTO> all() {
		return categoryService.getAll();
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody CategoryDTO dto) {
		return ResponseDTO.ok(categoryService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(categoryService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody CategoryDTO dto) {
		return ResponseDTO.ok(categoryService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(categoryService.delete(id));
	}

}
