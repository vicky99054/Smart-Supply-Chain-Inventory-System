package Inventory.Service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;
    private String productName;
    private String category;
    private String warehouseLocation;

    private int stockQuantity;
    private int reorderLevel;
    private double unitPrice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
