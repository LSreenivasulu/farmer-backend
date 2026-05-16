package com.example.farmer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "farmer_id")
    private String farmerId;

    @Column(name = "product_name")
    private String productName;

    private int quantity;

    @Column(name = "product_price")
    private double productPrice;

    private String description;
    private String category;
    private String status;

    @Column(name = "admin_response")
    private String adminResponse;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "total_amount")
    private Double totalAmount;

    // 🔥 🔥 RELATIONSHIP (IMPORTANT)
    @ManyToOne
    @JoinColumn(name = "user_id")   // FK column in orders table
    @JsonBackReference
    private User user;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = "PENDING";
        this.totalAmount = this.quantity * this.productPrice;
    }

    // GETTERS & SETTERS

    public Long getOrderId() { return orderId; }
    public String getFarmerId() { return farmerId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getProductPrice() { return productPrice; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getStatus() { return status; }
    public String getAdminResponse() { return adminResponse; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Double getTotalAmount() { return totalAmount; }

    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public void setFarmerId(String farmerId) { this.farmerId = farmerId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
    public void setStatus(String status) { this.status = status; }
    public void setAdminResponse(String adminResponse) { this.adminResponse = adminResponse; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}