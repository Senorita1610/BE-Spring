package com.sportshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportshop.entity.PromotionEntity;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {

}
