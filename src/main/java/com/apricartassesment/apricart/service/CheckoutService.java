package com.apricartassesment.apricart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CheckoutService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity processCheckout(List<Long> productIds, Long userId) {
        logger.info("Processing checkout for user with ID: {}", userId);
        
        // Retrieve products from product repository
        List<ProductEntity> products = productRepository.findAllById(productIds);
        logger.info("Retrieved {} products from the repository", products.size());

        // Calculate total amount payable
        double totalAmount = calculateTotal(products);
        logger.info("Total amount payable: {}", totalAmount);

        // Create order entity
        OrderEntity order = createOrder(userId, products, totalAmount);
        logger.info("Created order for user with ID: {}", userId);

        // Simulate payment processing
        boolean paymentStatus = processPayment(totalAmount);

        // If payment is successful, place the order
        if (paymentStatus) {
            order = placeOrder(order);
            logger.info("Order placed successfully for user with ID: {}", userId);
        } else {
            logger.error("Payment processing failed for user with ID: {}", userId);
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
        order.setTotalPrice(totalAmount); 
        return orderRepository.save(order);
    }

    private boolean processPayment(double amount) {
        // Implement payment processing logic here
        // For demonstration purposes, assume payment is always successful
        logger.info("Payment processed successfully");
        return true;
    }

    private OrderEntity placeOrder(OrderEntity order) {
        order.setStatus("placed");
        return orderRepository.save(order);
    }
}
