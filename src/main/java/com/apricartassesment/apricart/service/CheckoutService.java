package com.apricartassesment.apricart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.apricartassesment.apricart.entity.OrderEntity;
import com.apricartassesment.apricart.entity.ProductEntity;
import com.apricartassesment.apricart.helpers.PaymentProcessingException;
import com.apricartassesment.apricart.repository.OrderRepository;
import com.apricartassesment.apricart.repository.ProductRepository;

@Service
public class CheckoutService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	public OrderEntity processCheckout(List<Long> productIds, Long userId) {
		// Retrieve products from product repository
		List<ProductEntity> products = productRepository.findAllById(productIds);

		// Calculate total amount payable
		double totalAmount = calculateTotal(products);

		// Create order entity
		OrderEntity order = createOrder(userId, products, totalAmount);

		// Simulate payment processing
		boolean paymentStatus = processPayment(totalAmount);

		// If payment is successful, place the order
		if (paymentStatus) {
			order = placeOrder(order);
		} else {
			throw new PaymentProcessingException("Payment processing failed.");
		}

		return order;
	}

	private double calculateTotal(List<ProductEntity> products) {
		double total = 0;
		for (ProductEntity product : products) {
			total += product.getPrice();
		}
		return total;
	}

	private OrderEntity createOrder(Long userId, List<ProductEntity> products, double totalAmount) {
		OrderEntity order = new OrderEntity();
		order.setUserId(userId);
		order.setProducts(products);
		order.setTotalPrice(totalAmount); // Assuming totalAmount represents the total price of the order
		return orderRepository.save(order);
	}

	private boolean processPayment(double amount) {
		// Implement payment processing logic here
		// For demonstration purposes, assume payment is always successful
		return true;
	}

	private OrderEntity placeOrder(OrderEntity order) {
		// Update order status to "placed"
		order.setStatus("placed");
		// Save the updated order to the repository
		return orderRepository.save(order);
	}
}
