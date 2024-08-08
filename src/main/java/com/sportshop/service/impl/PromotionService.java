package com.sportshop.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.PromotionConverter;
import com.sportshop.dto.PromotionDTO;
import com.sportshop.entity.PromotionEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.PromotionRepository;
import com.sportshop.service.IPromotionService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;

@Service
public class PromotionService implements IPromotionService {
	@Autowired
	PromotionRepository promotionRepo;
	@Autowired
	PromotionConverter promotionConverter;

	@Override
	public List<PromotionDTO> getAll() {
		List<PromotionEntity> list = promotionRepo.findAll();
		List<PromotionDTO> result = new ArrayList<>();
		for (PromotionEntity en : list) {
			PromotionDTO dto = promotionConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public PromotionDTO get(Long id) {
		PromotionEntity en = promotionRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("promotion.not.exists", "id", id));
		});
		PromotionDTO dto = promotionConverter.toDTO(en);
		return dto;
	}

	@Override
	public PromotionDTO create(PromotionDTO request) {
		String content = request.getContent();
		LocalDateTime startDate = request.getStartDate();
		LocalDateTime endDate = request.getEndDate();
		InputValidateUtil.validateNotNull("content", content);
		PromotionEntity promotion = promotionConverter.toEntity(request);
		PromotionDTO dto = promotionConverter.toDTO(promotionRepo.save(promotion));
		return dto;
	}

	@Override
	public PromotionDTO update(PromotionDTO request) {
		Long promotionId = request.getPromotionId();
		String content = request.getContent();
		LocalDateTime startDate = request.getStartDate();
		LocalDateTime endDate = request.getEndDate();
		InputValidateUtil.validateNotNull("content", content);
		PromotionEntity promotion = promotionRepo.findById(promotionId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("promotion.not.exists", "id", promotionId));
		});
		PromotionEntity en = promotionConverter.toEntity(request);
		promotion.setContent(en.getContent());
		promotion.setStartDate(en.getStartDate());
		promotion.setEndDate(en.getEndDate());
		promotion.setPackageEntity(en.getPackageEntity());
		PromotionDTO dto = promotionConverter.toDTO(promotionRepo.save(promotion));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		promotionRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("promotion.not.exists", "id", id));
		});
		promotionRepo.deleteById(id);
		return Boolean.TRUE;
	}

}
