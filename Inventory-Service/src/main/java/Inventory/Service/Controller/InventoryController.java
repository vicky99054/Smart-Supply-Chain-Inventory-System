package Inventory.Service.Controller;

import Inventory.Service.Entity.Product;
import Inventory.Service.Service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return inventoryService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @PutMapping("/{productCode}/stock")
    public Product updateStock(@PathVariable String productCode, @RequestParam int quantityChange) {
        return inventoryService.updateStock(productCode, quantityChange);
    }
}
