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
public class SupplierDTO {
	private Long supplierId;
	private String address;
	private String description;
	private String name;
	private String product;
	private Integer quantity;

}
