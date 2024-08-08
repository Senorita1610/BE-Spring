package com.sportshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDTO {
	private Long billId;
	private Float totalPrice;
	private CustomerDTO customer;
	private EmployeeDTO employee;
	private PaymentMethodDTO paymentMethod;
	private PromotionDTO promotion;
	private PackageDTO packageDTO;

}
