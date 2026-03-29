package com.Order_Service.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;
    private String customerPhone;

    private String productCode;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, CONFIRMED, PAID, SHIPPED, DELIVERED, CANCELLED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
