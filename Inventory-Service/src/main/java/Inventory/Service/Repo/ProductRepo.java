package Inventory.Service.Repo;

import Inventory.Service.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(String productCode);
}
