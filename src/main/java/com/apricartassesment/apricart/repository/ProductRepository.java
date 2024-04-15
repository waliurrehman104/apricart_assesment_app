package com.apricartassesment.apricart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apricartassesment.apricart.entity.ProductEntity;

public interface ProductRepository  extends JpaRepository<ProductEntity, Long>{

}
