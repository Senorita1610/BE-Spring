package com.sportshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class AccountDTO {
	private Long accountId;
	private String fullName;
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private AccountTypeDTO accountType;

}
