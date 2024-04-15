package com.apricartassesment.apricart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apricartassesment.apricart.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

	List<CartEntity> findByUserId(String userId);

}
