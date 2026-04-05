package com.example.farmer.service;

import com.example.farmer.model.Order;
import com.example.farmer.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // ✅ GET ALL ORDERS (ADMIN)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ CREATE ORDER (Farmer)
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // ✅ GET FARMER ORDERS
    public List<Order> getFarmerOrders(String farmerId) {
        return orderRepository.findByFarmerId(farmerId);
    }

    // ✅ UPDATE STATUS (ADMIN)
    public Order updateOrderStatus(Long orderId, String status, String response) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        order.setAdminResponse(response);

        return orderRepository.save(order);
    }
}