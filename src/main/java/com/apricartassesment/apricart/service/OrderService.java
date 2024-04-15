package com.apricartassesment.apricart.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apricartassesment.apricart.entity.OrderEntity;
import com.apricartassesment.apricart.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity placeOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity updateOrderStatus(Long orderId, String status) {
        OrderEntity order = getOrderById(orderId);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public List<OrderEntity> getOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public OrderEntity getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public void cancelOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public double getOrderTotal(Long orderId) {
        OrderEntity order = getOrderById(orderId);
        return order.getTotalPrice();
    }
}
