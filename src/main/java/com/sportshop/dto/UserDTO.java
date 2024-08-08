package com.sportshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sportshop.entity.UserStatusEntity;
import com.sportshop.entity.UserTypeEntity;

public class UserDTO {
	public Long id;
	public String nameUser;
	public String phone;
	public String email;
	public String address;
	@JsonProperty(access = Access.WRITE_ONLY)
	public String password;
	public String verify;
	public String regtime;
	@JsonProperty(access = Access.WRITE_ONLY)
	public String salt;
	public UserTypeEntity userType;

	public UserStatusEntity userStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public UserTypeEntity getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEntity userType) {
		this.userType = userType;
	}

	public UserStatusEntity getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatusEntity userStatus) {
		this.userStatus = userStatus;
	}

}
