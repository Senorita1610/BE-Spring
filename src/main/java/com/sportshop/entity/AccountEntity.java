package com.sportshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountEntity {
	@Id
	@Column(name = "id_account")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "id_account_type")
	private AccountTypeEntity accountType;

}
