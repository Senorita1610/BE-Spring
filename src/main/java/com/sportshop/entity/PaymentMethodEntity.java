package com.sportshop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "payment_method")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PaymentMethodEntity {
	@Id
	@Column(name = "id_payment_method")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentMethodId;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "paymentMethod")
	@JsonIgnore
	private List<BillEntity> bills;

}
