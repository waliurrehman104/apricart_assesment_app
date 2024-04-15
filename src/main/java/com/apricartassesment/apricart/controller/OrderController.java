package com.apricartassesment.apricart.controller;

import com.apricartassesment.apricart.entity.OrderEntity;
import com.apricartassesment.apricart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/place")
	public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderEntity order) {
		OrderEntity placedOrder = orderService.placeOrder(order);
		return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
	}

	@PutMapping("/updateStatus/{orderId}/{status}")
	public ResponseEntity<OrderEntity> updateOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
		OrderEntity updatedOrder = orderService.updateOrderStatus(orderId, status);
		return ResponseEntity.ok(updatedOrder);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<OrderEntity>> getOrdersByUserId(@PathVariable Long userId) {
		List<OrderEntity> orders = orderService.getOrderByUserId(userId);
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long orderId) {
		OrderEntity order = orderService.getOrderById(orderId);
		return ResponseEntity.ok(order);
	}

	@DeleteMapping("/cancel/{orderId}")
	public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
		orderService.cancelOrder(orderId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/total/{orderId}")
	public ResponseEntity<Double> getOrderTotal(@PathVariable Long orderId) {
		double total = orderService.getOrderTotal(orderId);
		return ResponseEntity.ok(total);
	}
}
