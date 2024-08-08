package com.sportshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportshop.entity.PackageEntity;

public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
	Optional<PackageEntity> findBySlug(String slug);
	Optional<PackageEntity> findByName(String name);
}
