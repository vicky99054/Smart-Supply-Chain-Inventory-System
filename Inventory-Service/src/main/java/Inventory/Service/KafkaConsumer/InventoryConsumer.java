package Inventory.Service.KafkaConsumer;


import Inventory.Service.Service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InventoryConsumer {
    private final InventoryService inventoryService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public InventoryConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "order-events", groupId = "inventory-service-group")
    public void consumeOrderEvent(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String productCode = jsonNode.get("productCode").asText();
            int quantity = jsonNode.get("quantity").asInt();

            // Reduce stock when order is created
            inventoryService.updateStock(productCode, -quantity);

            System.out.println("Stock updated for product: " + productCode);
        } catch (Exception e) {
            System.err.println("Failed to process order event: " + e.getMessage());
        }
    }
}
