package com.Order_Service.OrderEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderRequest {
    @NotBlank
    private String customerName;

    @Email
    private String customerEmail;

    @NotBlank
    private String productCode;

    @Min(1)
    private int quantity;

    @Positive
    private double unitPrice;
}
