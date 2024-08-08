package com.sportshop.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportshop.dto.BillDTO;
import com.sportshop.entity.BillEntity;

@Component
public class BillConverter {
	@Autowired
	CustomerConverter customerConverter;
	@Autowired
	EmployeeConverter employeeConverter;
	@Autowired
	PaymentMethodConverter paymentMethodConverter;
	@Autowired
	PromotionConverter promotionConverter;
	@Autowired
	PackageConverter packageConverter;

	public BillEntity toEntity(BillDTO dto) {
		BillEntity en = new BillEntity();
		en.setBillId(dto.getBillId());
		en.setTotalPrice(dto.getTotalPrice());
		if (dto.getCustomer() != null) {
			en.setCustomer(customerConverter.toEntity(dto.getCustomer()));
		} else {
			en.setCustomer(null);
		}
		if (dto.getEmployee() != null) {
			en.setEmployee(employeeConverter.toEntity(dto.getEmployee()));
		} else {
			en.setEmployee(null);
		}
		if (dto.getPaymentMethod() != null) {
			en.setPaymentMethod(paymentMethodConverter.toEntity(dto.getPaymentMethod()));
		} else {
			en.setPaymentMethod(null);
		}
		if (dto.getPromotion() != null) {
			en.setPromotion(promotionConverter.toEntity(dto.getPromotion()));
		} else {
			en.setPromotion(null);
		}
		if (dto.getPackageDTO() != null) {
			en.setPackageEntity(packageConverter.toEntity(dto.getPackageDTO()));
		} else {
			en.setPackageEntity(null);
		}
		return en;
	}

	public BillDTO toDTO(BillEntity en) {
		BillDTO dto = new BillDTO();
		dto.setBillId(en.getBillId());
		dto.setTotalPrice(en.getTotalPrice());
		if (en.getCustomer() != null) {
			dto.setCustomer(customerConverter.toDTO(en.getCustomer()));
		} else {
			dto.setCustomer(null);
		}
		if (en.getEmployee() != null) {
			dto.setEmployee(employeeConverter.toDTO(en.getEmployee()));
		} else {
			dto.setEmployee(null);
		}
		if (en.getPaymentMethod() != null) {
			dto.setPaymentMethod(paymentMethodConverter.toDTO(en.getPaymentMethod()));
		} else {
			dto.setPaymentMethod(null);
		}
		if (en.getPromotion() != null) {
			dto.setPromotion(promotionConverter.toDTO(en.getPromotion()));
		} else {
			dto.setPromotion(null);
		}
		if (en.getPackageEntity() != null) {
			dto.setPackageDTO(packageConverter.toDTO(en.getPackageEntity()));
		} else {
			dto.setPackageDTO(null);
		}
		return dto;
	}

}
