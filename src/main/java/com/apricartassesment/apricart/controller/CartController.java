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

import com.apricartassesment.apricart.entity.CartEntity;
import com.apricartassesment.apricart.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public ResponseEntity<CartEntity> addToCart(@RequestBody CartEntity cartItem) {
		CartEntity addedItem = cartService.addToCart(cartItem);
		return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
	}

	@PutMapping("/update/{cartId}/{quantity}")
	public ResponseEntity<CartEntity> updateCart(@PathVariable Long cartId, @PathVariable int quantity) {
		CartEntity updatedItem = cartService.updateCart(cartId, quantity);
		return ResponseEntity.ok(updatedItem);
	}

	@DeleteMapping("/remove/{cartId}")
	public ResponseEntity<Void> removeFromCart(@PathVariable Long cartId) {
		cartService.removeFromCart(cartId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/clear")
	public ResponseEntity<Void> clearCart() {
		cartService.clearCart();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<CartEntity>> getCartItems(@PathVariable String userId) {
		List<CartEntity> cartItems = cartService.getCartItems(userId);
		return ResponseEntity.ok(cartItems);
	}

	@GetMapping("/total/{userId}")
	public ResponseEntity<Double> getCartTotal(@PathVariable String userId) {
		double total = cartService.getCartTotal(userId);
		return ResponseEntity.ok(total);
	}
}
