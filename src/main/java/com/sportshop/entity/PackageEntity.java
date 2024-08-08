package com.sportshop.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "package")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PackageEntity {
	@Id
	@Column(name = "id_package")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long packageId;
	@Column(name = "sub_desc", columnDefinition = "TEXT")
	private String subDesc;
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Float price;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "release_date")
	private LocalDateTime releaseDate;
	@Column(name = "validity")
	private Integer validity;
	@Column(name = "slug")
	private String slug;

	@ManyToOne
	@JoinColumn(name = "id_category")
	private CategoryEntity category;

	@ManyToOne
	@JoinColumn(name = "id_supplier")
	private SupplierEntity supplier;

	@OneToMany(mappedBy = "packageEntity")
	@JsonIgnore
	private List<PromotionEntity> promotions;

	@OneToMany(mappedBy = "packageEntity")
	@JsonIgnore
	private List<BillEntity> bills;

}
