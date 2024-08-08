package com.sportshop.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PromotionEntity {
	@Id
	@Column(name = "id_promotion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long promotionId;
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	@Column(name = "end_date")
	private LocalDateTime endDate;
	@Column(name = "start_date")
	private LocalDateTime startDate;

	@ManyToOne
	@JoinColumn(name = "id_package")
	private PackageEntity packageEntity;

	@OneToMany(mappedBy = "promotion")
	@JsonIgnore
	private List<BillEntity> bills;

}
