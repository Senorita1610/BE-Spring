package com.sportshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportshop.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	Optional<CategoryEntity> findByDescription(String description);

}
