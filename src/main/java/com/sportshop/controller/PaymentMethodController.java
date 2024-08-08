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

import com.sportshop.dto.PaymentMethodDTO;
import com.sportshop.dto.ResponseDTO;
import com.sportshop.service.IPaymentMethodService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/payment-method")
public class PaymentMethodController {
	@Autowired
	IPaymentMethodService paymentMethodService;

	@GetMapping("/all")
	public List<PaymentMethodDTO> all() {
		return paymentMethodService.getAll();
	}

	@PostMapping("/create")
	public ResponseDTO<Object> create(@RequestBody PaymentMethodDTO dto) {
		return ResponseDTO.ok(paymentMethodService.create(dto));
	}

	@GetMapping("/{id}")
	ResponseDTO<Object> get(@PathVariable Long id) {
		return ResponseDTO.ok(paymentMethodService.get(id));
	}

	@PutMapping("/update")
	ResponseDTO<Object> update(@RequestBody PaymentMethodDTO dto) {
		return ResponseDTO.ok(paymentMethodService.update(dto));
	}

	@DeleteMapping("/{id}")
	ResponseDTO<Object> delete(@PathVariable Long id) {
		return ResponseDTO.ok(paymentMethodService.delete(id));
	}
}
