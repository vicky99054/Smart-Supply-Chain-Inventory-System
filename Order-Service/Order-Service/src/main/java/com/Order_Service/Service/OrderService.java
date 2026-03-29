package com.Order_Service.Service;

import com.Order_Service.OrderEntity.Order;
import com.Order_Service.OrderEntity.OrderRequest;
import com.Order_Service.OrderEntity.OrderResponse;
import com.Order_Service.OrderEntity.OrderStatus;
import com.Order_Service.Repo.OrderRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private final OrderRepo orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepo repo, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = repo;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setProductCode(request.getProductCode());
        order.setQuantity(request.getQuantity());
        order.setUnitPrice(request.getUnitPrice());
        order.setTotalAmount(request.getQuantity() * request.getUnitPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order saved = orderRepository.save(order);

        publishEvent("Order Created", saved);

        return mapToResponse(saved);
    }

    public List<OrderResponse> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponse updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        Order updated = orderRepository.save(order);

        publishEvent("Order Updated", updated);

        return mapToResponse(updated);
    }

    private void publishEvent(String action, Order order) {
        try {
            String eventPayload = objectMapper.writeValueAsString(order);
            kafkaTemplate.send("order-events", eventPayload);
            logger.info("{}: {}", action, eventPayload);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize order event", e);
        }
    }

    private OrderResponse mapToResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setCustomerEmail(order.getCustomerEmail());
        response.setProductCode(order.getProductCode());
        response.setQuantity(order.getQuantity());
        response.setUnitPrice(order.getUnitPrice());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        return response;
    }
}
