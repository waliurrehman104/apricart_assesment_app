package com.apricartassesment.apricart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apricartassesment.apricart.entity.OrderEntity;
import com.apricartassesment.apricart.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity placeOrder(OrderEntity order) {
        logger.info("Placing order: {}", order);
        return orderRepository.save(order);
    }

    public OrderEntity updateOrderStatus(Long orderId, String status) {
        logger.info("Updating order status for orderId: {} to {}", orderId, status);
        OrderEntity order = getOrderById(orderId);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public List<OrderEntity> getOrderByUserId(Long userId) {
        logger.info("Fetching orders for userId: {}", userId);
        return orderRepository.findByUserId(userId);
    }

    public OrderEntity getOrderById(Long orderId) {
        logger.info("Fetching order with orderId: {}", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public void cancelOrder(Long orderId) {
        logger.info("Canceling order with orderId: {}", orderId);
        orderRepository.deleteById(orderId);
    }

    public double getOrderTotal(Long orderId) {
        logger.info("Fetching total price for order with orderId: {}", orderId);
        OrderEntity order = getOrderById(orderId);
        return order.getTotalPrice();
    }
}
