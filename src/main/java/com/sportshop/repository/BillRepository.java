package com.sportshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportshop.entity.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

}
