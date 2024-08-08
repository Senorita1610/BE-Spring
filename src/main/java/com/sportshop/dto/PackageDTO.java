package com.sportshop.dto;

import java.time.LocalDateTime;

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
public class PackageDTO {
	private Long packageId;
	private String subDesc;
	private String description;
	private String name;
	private Float price;
	private Integer quantity;
	private LocalDateTime releaseDate;
	private Integer validity;
	private String slug;
	private CategoryDTO category;
	private SupplierDTO supplier;

}
