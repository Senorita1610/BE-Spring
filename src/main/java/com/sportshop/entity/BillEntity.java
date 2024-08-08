package com.sportshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BillEntity {
	@Id
	@Column(name = "id_bill")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billId;

	@Column(name = "total_price")
	private Float totalPrice;

	@ManyToOne
	@JoinColumn(name = "id_customer")
	private CustomerEntity customer;

	@ManyToOne
	@JoinColumn(name = "id_employee")
	private EmployeeEntity employee;

	@ManyToOne
	@Nullable
	@JoinColumn(name = "id_payment_method")
	private PaymentMethodEntity paymentMethod;

	@ManyToOne
	@JoinColumn(name = "id_promotion")
	private PromotionEntity promotion;

	@ManyToOne
	@JoinColumn(name = "id_package")
	private PackageEntity packageEntity;

}
