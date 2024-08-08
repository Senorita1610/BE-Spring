package com.sportshop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "account_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountTypeEntity {
	@Id
	@Column(name = "id_account_type")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountTypeId;
	@Column(name = "name")
	private String name;
	@Column(name = "position")
	private String position;
	@Column(name = "role")
	private String role;
	@OneToMany(mappedBy = "accountType")
	@JsonIgnore
	private List<AccountEntity> accounts;

	public AccountTypeEntity(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
}
