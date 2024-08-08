package com.sportshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.CategoryConverter;
import com.sportshop.dto.CategoryDTO;
import com.sportshop.entity.CategoryEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.CategoryRepository;
import com.sportshop.service.ICategoryService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;
import com.sportshop.utils.Slugify;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	CategoryConverter categoryConverter;

	@Override
	public List<CategoryDTO> getAll() {
		List<CategoryEntity> list = categoryRepo.findAll();
		List<CategoryDTO> result = new ArrayList<>();
		for (CategoryEntity en : list) {
			CategoryDTO dto = categoryConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public CategoryDTO get(Long id) {
		CategoryEntity en = categoryRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("category.not.exists", "id", id));
		});
		CategoryDTO dto = categoryConverter.toDTO(en);
		return dto;
	}

	@Override
	public CategoryDTO create(CategoryDTO request) {
		String name = request.getName();
		String description = request.getDescription();
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("description", description);
		categoryRepo.findByDescription(description).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("category.exists", "description", description));
		});
		CategoryEntity category = categoryConverter.toEntity(request);
		category.setSlug(Slugify.toSlug(request.getName()));
		CategoryDTO dto = categoryConverter.toDTO(categoryRepo.save(category));
		return dto;
	}

	@Override
	public CategoryDTO update(CategoryDTO request) {
		Long categoryId = request.getCategoryId();
		String name = request.getName();
		String description = request.getDescription();
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("description", description);
		categoryRepo.findByDescription(description).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("category.exists", "description", description));
		});
		CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("category.not.exists", "id", categoryId));
		});
		CategoryEntity en = categoryConverter.toEntity(request);
		category.setName(en.getName());
		category.setDescription(en.getDescription());
		category.setSlug(Slugify.toSlug(en.getName()));
		CategoryDTO dto = categoryConverter.toDTO(categoryRepo.save(category));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		categoryRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("category.not.exists", "id", id));
		});
		categoryRepo.deleteById(id);
		return Boolean.TRUE;
	}
}
