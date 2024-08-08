package com.sportshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportshop.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	Optional<AccountEntity> findByUsername(String username);

}
