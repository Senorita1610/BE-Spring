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
public class EmployeeDTO {

	private Long employeeId;
	private String address;
	private LocalDate birthday;
	private Integer gender;
	private LocalDate joinDate;
	private String name;
	private String phoneNumber;
	private AccountDTO account;

}
