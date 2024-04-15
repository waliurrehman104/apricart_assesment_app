package com.apricartassesment.apricart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apricartassesment.apricart.entity.ProductEntity;
import com.apricartassesment.apricart.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
		ProductEntity createdProduct = productService.createProduct(product);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long productId,
			@RequestBody ProductEntity product) {
		ProductEntity updatedProduct = productService.updateProduct(productId, product);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductEntity> getProductById(@PathVariable Long productId) {
		ProductEntity product = productService.getProductById(productId);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/{productId}/price")
	public ResponseEntity<Double> getProductPrice(@PathVariable Long productId) {
		double price = productService.getProductPrice(productId);
		return ResponseEntity.ok(price);
	}

	@GetMapping
	public ResponseEntity<List<ProductEntity>> getAllProducts() {
		List<ProductEntity> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
}
