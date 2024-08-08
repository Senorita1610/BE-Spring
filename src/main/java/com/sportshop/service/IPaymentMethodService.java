package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.PaymentMethodDTO;

public interface IPaymentMethodService {
	List<PaymentMethodDTO> getAll();

	PaymentMethodDTO get(Long id);

	PaymentMethodDTO create(PaymentMethodDTO request);

	PaymentMethodDTO update(PaymentMethodDTO paymentMethodDTO);

	boolean delete(Long id);
}
