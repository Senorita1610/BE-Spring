package com.sportshop.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EmployeeEntity {
	@Id
	@Column(name = "id_employee")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	@Column(name = "address")
	private String address;
	@Column(name = "birthday")
	private LocalDate birthday;
	@Column(name = "gender")
	private Integer gender;
	@Column(name = "join_date")
	private LocalDate joinDate;
	@Column(name = "name")
	private String name;
	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToOne
	@JoinColumn(name = "id_account")
	private AccountEntity account;

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<BillEntity> bills;

}
