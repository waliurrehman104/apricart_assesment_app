package com.apricartassesment.apricart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apricartassesment.apricart.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	List<OrderEntity> findByUserId(Long userId);

}
