package com.apricartassesment.apricart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apricartassesment.apricart.entity.ProductEntity;
import com.apricartassesment.apricart.helpers.*;
import com.apricartassesment.apricart.repository.ProductRepository;
	
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	

	public ProductEntity createProduct(ProductEntity product) {
		return productRepository.save(product);
	}

	public ProductEntity updateProduct(Long productId, ProductEntity product) {
		if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
		}
		product.setProductId(productId);
		return productRepository.save(product);
	}

	public void deleteProduct(Long productId) {
		if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
		}
		productRepository.deleteById(productId);
	}

	public ProductEntity getProductById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
	}
	public Double getProductPrice(Long productId) {
	    Optional<ProductEntity> optionalProduct = productRepository.findById(productId);
	    if (optionalProduct.isPresent()) {
	        return optionalProduct.get().getPrice();
	    } else {
	        return null;
	    }
	}


	public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
	}
	
}
