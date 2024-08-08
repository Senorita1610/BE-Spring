package com.sportshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportshop.entity.AccountTypeEntity;

public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Long> {
	Optional<AccountTypeEntity> findByRole(String role);

}
