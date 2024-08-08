package com.sportshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportshop.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	Optional<EmployeeEntity> findByPhoneNumber(String phoneNumber);
}
