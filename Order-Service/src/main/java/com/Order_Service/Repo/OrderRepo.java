package com.Order_Service.Repo;

import com.Order_Service.OrderEntity.Order;
import com.Order_Service.OrderEntity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order , Long> {
    List<Order> findByStatus(OrderStatus status);
}
