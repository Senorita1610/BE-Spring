package com.sportshop.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportshop.dto.PromotionDTO;
import com.sportshop.entity.PromotionEntity;

@Component
public class PromotionConverter {
	@Autowired
	PackageConverter packageConverter;

	public PromotionEntity toEntity(PromotionDTO dto) {
		PromotionEntity en = new PromotionEntity();
		en.setPromotionId(dto.getPromotionId());
		en.setContent(dto.getContent());
		en.setStartDate(dto.getStartDate());
		en.setEndDate(dto.getEndDate());
		en.setPackageEntity(packageConverter.toEntity(dto.getPackageDTO()));
		return en;
	}

	public PromotionDTO toDTO(PromotionEntity en) {
		PromotionDTO dto = new PromotionDTO();
		dto.setPromotionId(en.getPromotionId());
		dto.setContent(en.getContent());
		dto.setStartDate(en.getStartDate());
		dto.setEndDate(en.getEndDate());
		dto.setPackageDTO(packageConverter.toDTO(en.getPackageEntity()));
		return dto;
	}

}
