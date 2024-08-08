package com.sportshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportshop.entity.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
	Optional<SupplierEntity> findByName(String name);
}
