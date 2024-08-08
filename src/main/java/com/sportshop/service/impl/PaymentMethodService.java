package com.sportshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.PaymentMethodConverter;
import com.sportshop.dto.PaymentMethodDTO;
import com.sportshop.entity.PaymentMethodEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.PaymentMethodRepository;
import com.sportshop.service.IPaymentMethodService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;

@Service
public class PaymentMethodService implements IPaymentMethodService {
	@Autowired
	PaymentMethodRepository paymentMethodRepo;
	@Autowired
	PaymentMethodConverter paymentMethodConverter;

	@Override
	public List<PaymentMethodDTO> getAll() {
		List<PaymentMethodEntity> list = paymentMethodRepo.findAll();
		List<PaymentMethodDTO> result = new ArrayList<>();
		for (PaymentMethodEntity en : list) {
			PaymentMethodDTO dto = paymentMethodConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public PaymentMethodDTO get(Long id) {
		PaymentMethodEntity en = paymentMethodRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("paymentmethod.not.exists", "id", id));
		});
		PaymentMethodDTO dto = paymentMethodConverter.toDTO(en);
		return dto;
	}

	@Override
	public PaymentMethodDTO create(PaymentMethodDTO request) {
		String name = request.getName();
		String description = request.getDescription();
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("description", description);
		paymentMethodRepo.findByName(name).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("paymentmethod.exists", "name", name));
		});
		PaymentMethodEntity en = PaymentMethodEntity.builder().build();
		BeanUtils.copyProperties(request, en);
		PaymentMethodDTO dto = paymentMethodConverter.toDTO(paymentMethodRepo.save(en));
		return dto;
	}

	@Override
	public PaymentMethodDTO update(PaymentMethodDTO request) {
		Long paymentMethodId = request.getPaymentMethodId();
		String name = request.getName();
		String description = request.getDescription();
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("description", description);
		paymentMethodRepo.findByName(name).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("paymentmethod.exists", "name", name));
		});
		PaymentMethodEntity en = paymentMethodRepo.findById(paymentMethodId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("paymentmethod.not.exists", "id", paymentMethodId));
		});
		
//		PaymentMethodEntity en = PaymentMethodEntity.builder().build();
//		BeanUtils.copyProperties(request, en);
		PaymentMethodDTO dto = paymentMethodConverter.toDTO(paymentMethodRepo.save(en));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		paymentMethodRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("paymentmethod.not.exists", "id", id));
		});
		paymentMethodRepo.deleteById(id);
		return Boolean.TRUE;
	}

}
