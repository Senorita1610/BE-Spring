package com.sportshop.response;

import com.sportshop.dto.AccountDTO;

public class AuthResponse extends BaseResponse {
	public AccountDTO user;

	public AuthResponse(Integer status, String message, AccountDTO user, String token) {
		super();
		this.user = user;
		this.status = status;
		this.message = message;
		this.token = token;
	}

	public AuthResponse(Integer status, String message, AccountDTO user) {
		super();
		this.user = user;
		this.status = status;
		this.message = message;
	}

	public AuthResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
}
