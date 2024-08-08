package com.sportshop.converter;

import org.springframework.stereotype.Component;

import com.sportshop.dto.SupplierDTO;
import com.sportshop.entity.SupplierEntity;

@Component
public class SupplierConverter {
	public SupplierEntity toEntity(SupplierDTO dto) {
		SupplierEntity en = new SupplierEntity();
		en.setSupplierId(dto.getSupplierId());
		en.setAddress(dto.getAddress());
		en.setDescription(dto.getDescription());
		en.setName(dto.getName());
		en.setProduct(dto.getProduct());
		en.setQuantity(dto.getQuantity());
		return en;
	}

	public SupplierDTO toDTO(SupplierEntity en) {
		SupplierDTO dto = new SupplierDTO();
		dto.setSupplierId(en.getSupplierId());
		dto.setAddress(en.getAddress());
		dto.setDescription(en.getDescription());
		dto.setName(en.getName());
		dto.setProduct(en.getProduct());
		dto.setQuantity(en.getQuantity());
		return dto;
	}

}
