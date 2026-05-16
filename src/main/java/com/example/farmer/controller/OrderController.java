package com.example.farmer.controller;

import com.example.farmer.model.Order;
import com.example.farmer.model.User;
import com.example.farmer.repository.OrderRepository;
import com.example.farmer.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;

    public OrderController(OrderRepository orderRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    // ✅ CREATE ORDER (NOW LINKED TO USER)
    @PostMapping("/{userId}")
    public Order createOrder(@PathVariable Long userId, @RequestBody Order order) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setUser(user);  // 🔥 IMPORTANT

        return orderRepo.save(order);
    }

    // ✅ ADMIN: GET ALL ORDERS
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    // ✅ USER: GET OWN ORDERS (USING USER ID)
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderRepo.findByUserId(userId);
    }

    // ❌ OLD (REMOVE THIS)
    // @GetMapping("/farmer/{farmerId}")

    // ✅ ADMIN: ACCEPT / DECLINE / COMPLETE
    @PutMapping("/{id}/response")
    public Order updateOrderResponse(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(body.get("status"));
        order.setAdminResponse(body.get("adminResponse"));

        return orderRepo.save(order);
    }

    // ✅ DELETE (ONLY PENDING)
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {

        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getStatus().equals("PENDING")) {
            throw new RuntimeException("Only pending orders can be deleted");
        }

        orderRepo.deleteById(id);
        return "Deleted";
    }
}