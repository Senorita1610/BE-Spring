package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.PromotionDTO;

public interface IPromotionService {
	List<PromotionDTO> getAll();

	PromotionDTO get(Long id);

	PromotionDTO create(PromotionDTO promotionDTO);

	PromotionDTO update(PromotionDTO promotionDTO);

	boolean delete(Long id);

}
