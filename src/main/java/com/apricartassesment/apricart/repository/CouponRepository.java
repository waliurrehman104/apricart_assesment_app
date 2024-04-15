package com.apricartassesment.apricart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apricartassesment.apricart.entity.CouponEntity;

public interface CouponRepository  extends JpaRepository<CouponEntity, Long>{

	Optional<CouponEntity> findByCouponCode(String couponCode);

}
