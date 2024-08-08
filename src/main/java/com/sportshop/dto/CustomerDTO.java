package com.sportshop.dto;

import java.time.LocalDate;

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
public class CustomerDTO {
	private Long customerId;
	private String address;
	private LocalDate birthday;
	private Integer gender;
	private String name;
	private String phoneNumber;
	private Integer usagePeriod;
	private AccountDTO account;

}
