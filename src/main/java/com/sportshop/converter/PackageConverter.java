package com.sportshop.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportshop.dto.PackageDTO;
import com.sportshop.entity.PackageEntity;

@Component
public class PackageConverter {
	@Autowired
	CategoryConverter categoryConverter;
	@Autowired
	SupplierConverter supplierConverter;

	public PackageEntity toEntity(PackageDTO dto) {
		PackageEntity en = new PackageEntity();
		en.setPackageId(dto.getPackageId());
		en.setSubDesc(dto.getSubDesc());
		en.setDescription(dto.getDescription());
		en.setName(dto.getName());
		en.setPrice(dto.getPrice());
		en.setQuantity(dto.getQuantity());
		en.setReleaseDate(dto.getReleaseDate());
		en.setValidity(dto.getValidity());
		en.setSlug(dto.getSlug());
		en.setCategory(categoryConverter.toEntity(dto.getCategory()));
		en.setSupplier(supplierConverter.toEntity(dto.getSupplier()));
		return en;
	}

	public PackageDTO toDTO(PackageEntity en) {
		PackageDTO dto = new PackageDTO();
		dto.setPackageId(en.getPackageId());
		dto.setSubDesc(en.getSubDesc());
		dto.setDescription(en.getDescription());
		dto.setName(en.getName());
		dto.setPrice(en.getPrice());
		dto.setQuantity(en.getQuantity());
		dto.setReleaseDate(en.getReleaseDate());
		dto.setValidity(en.getValidity());
		dto.setSlug(en.getSlug());
		dto.setCategory(categoryConverter.toDTO(en.getCategory()));
		dto.setSupplier(supplierConverter.toDTO(en.getSupplier()));
		return dto;
	}
}
