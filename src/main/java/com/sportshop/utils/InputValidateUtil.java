package com.sportshop.utils;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.sportshop.exception.ValidateCommonException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputValidateUtil {

	private InputValidateUtil() {
	}

	public static void validateNotNull(String name, String value) {
		Assert.isTrue(StringUtils.hasLength(value), MsgUtil.getMessage("input.required", name));
	}

	public static void validatePhoneNumber(String phoneNumber) {
		if (!StringUtils.hasLength(phoneNumber) || !phoneNumber.matches(Constant.Regex.PHONE_NUMBER)) {
			throw new ValidateCommonException(MsgUtil.getMessage("phone.number.not.format", phoneNumber));
		}
	}

	public static void validateEmail(String email) {
		if (!StringUtils.hasLength(email) || !email.matches(Constant.Regex.EMAIL)) {
			throw new ValidateCommonException(MsgUtil.getMessage("email.not.format", email));
		}
	}
}