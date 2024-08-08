package com.sportshop.converter;

import org.springframework.stereotype.Component;

import com.sportshop.dto.PaymentMethodDTO;
import com.sportshop.entity.PaymentMethodEntity;

@Component
public class PaymentMethodConverter {
	public PaymentMethodEntity toEntity(PaymentMethodDTO dto) {
		PaymentMethodEntity en = new PaymentMethodEntity();
		en.setPaymentMethodId(dto.getPaymentMethodId());
		en.setName(dto.getName());
		en.setDescription(dto.getDescription());
		return en;
	}

	public PaymentMethodDTO toDTO(PaymentMethodEntity en) {
		PaymentMethodDTO dto = new PaymentMethodDTO();
		dto.setPaymentMethodId(en.getPaymentMethodId());
		dto.setName(en.getName());
		dto.setDescription(en.getDescription());
		return dto;
	}

}
