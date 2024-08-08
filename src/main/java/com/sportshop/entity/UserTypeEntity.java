package com.sportshop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_type")
public class UserTypeEntity {
	@Id
	@Column(name = "ID_UT")
	private Long id;

	@Column(name = "Name_UT")
	private String name;

	@OneToMany(mappedBy = "userType")
	@JsonIgnore
	private List<UserEntity> users;

	public Long getId() {
		return id;
	}

	public UserTypeEntity() {

	}

	public UserTypeEntity(Long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

}
