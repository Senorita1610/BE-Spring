package com.sportshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_User")
	public Long id;

	@Column(name = "Name_User")
	public String nameUser;
	@Column(name = "Phone")
	public String phone;
	@Column(name = "Email")
	public String email;
	@Column(name = "Address")
	public String address;
	@Column
	public String password;
	@Column
	public String verify;
	@Column
	public String regtime;
	@Column
	public String salt;

	@ManyToOne
	@JoinColumn(name = "ID_UT")
	public UserTypeEntity userType;
	
	@ManyToOne
	@JoinColumn(name = "ID_UStatus")
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
