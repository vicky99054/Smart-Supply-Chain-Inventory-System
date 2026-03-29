package Inventory.Service.Service;


import Inventory.Service.Entity.Product;

import Inventory.Service.Repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {
    private final ProductRepo productRepo;

    public InventoryService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product updateStock(String productCode, int quantityChange) {
        Product product = productRepo.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStockQuantity(product.getStockQuantity() + quantityChange);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepo.save(product);
    }
}
