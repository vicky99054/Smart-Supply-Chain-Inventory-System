package com.Order_Service.OrderEntity;

import com.Order_Service.OrderEntity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String productCode;
    private int quantity;
    private double unitPrice;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
