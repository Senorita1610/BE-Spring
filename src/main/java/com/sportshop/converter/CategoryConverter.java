package com.sportshop.converter;

import org.springframework.stereotype.Component;

import com.sportshop.dto.CategoryDTO;
import com.sportshop.entity.CategoryEntity;

@Component
public class CategoryConverter {
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity en = new CategoryEntity();
		en.setCategoryId(dto.getCategoryId());
		en.setName(dto.getName());
		en.setDescription(dto.getDescription());
		en.setSlug(dto.getSlug());
		return en;
	}

	public CategoryDTO toDTO(CategoryEntity en) {
		CategoryDTO dto = new CategoryDTO();
		dto.setCategoryId(en.getCategoryId());
		dto.setName(en.getName());
		dto.setDescription(en.getDescription());
		dto.setSlug(en.getSlug());
		return dto;
	}
}
