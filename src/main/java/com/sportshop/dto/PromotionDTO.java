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
public class PromotionDTO {
	private Long promotionId;
	private String content;
	private LocalDateTime endDate;
	private LocalDateTime startDate;
	private PackageDTO packageDTO;

}
