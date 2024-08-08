package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.CategoryDTO;

public interface ICategoryService {
	List<CategoryDTO> getAll();

	CategoryDTO get(Long id);

	CategoryDTO create(CategoryDTO categoryDTO);

	CategoryDTO update(CategoryDTO categoryDTO);

	boolean delete(Long id);

}
