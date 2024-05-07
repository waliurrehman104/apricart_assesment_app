package com.apricartassesment.apricart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apricartassesment.apricart.entity.ProductEntity;
import com.apricartassesment.apricart.helpers.ResourceNotFoundException;
import com.apricartassesment.apricart.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity createProduct(ProductEntity product) {
        logger.info("Creating product: {}", product);
        return productRepository.save(product);
    }

    public ProductEntity updateProduct(Long productId, ProductEntity product) {
        logger.info("Updating product with ID {}: {}", productId, product);
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        product.setProductId(productId);
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        logger.info("Deleting product with ID {}", productId);
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
    }

    public ProductEntity getProductById(Long productId) {
        logger.info("Fetching product with ID {}", productId);
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
    }

    public Double getProductPrice(Long productId) {
        logger.info("Fetching price for product with ID {}", productId);
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getPrice();
        } else {
            return null;
        }
    }

    public List<ProductEntity> getAllProducts() {
        logger.info("Fetching all products");
        return productRepository.findAll();
    }
}
