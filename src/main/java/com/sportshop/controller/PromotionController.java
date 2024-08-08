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

import com.sportshop.dto.PromotionDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.service.IPromotionService;

@CrossOrigin
@RestController
@RequestMapping("/promotion")
public class PromotionController {
	@Autowired
	IPromotionService promotionService;

	@GetMapping("/all")
	public List<PromotionDTO> all() {
		return promotionService.getAll();
	}

	@PostMapping("/create")
	ResponseDTO<Object> create(@RequestBody PromotionDTO dto) {
		return ResponseDTO.ok(promotionService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(promotionService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody PromotionDTO dto) {
		return ResponseDTO.ok(promotionService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(promotionService.delete(id));
	}

}
