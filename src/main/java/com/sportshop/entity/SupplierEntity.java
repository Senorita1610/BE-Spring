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
@Table(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SupplierEntity {
	@Id
	@Column(name = "id_supplier")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplierId;
	@Column(name = "address")
	private String address;
	@Column(name = "description")
	private String description;
	@Column(name = "name")
	private String name;
	@Column(name = "product")
	private String product;
	@Column(name = "quantity")
	private Integer quantity;

	@OneToMany(mappedBy = "supplier")
	@JsonIgnore
	private List<PackageEntity> packages;

}
