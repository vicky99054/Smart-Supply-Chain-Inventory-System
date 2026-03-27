# 🚀 Smart Supply Chain & Inventory System

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Microservices-brightgreen)
![Kafka](https://img.shields.io/badge/Apache-Kafka-black)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Orchestration-326ce5)
![License](https://img.shields.io/badge/License-MIT-green)

A **modern cloud-native microservices platform** built with **Spring Boot, Apache Kafka, and Spring AI** to simulate a real-world **Supply Chain & Inventory Management System**.

This project demonstrates how **large-scale logistics platforms** manage:

* 📦 Orders
* 📊 Inventory
* 💳 Payments
* 🚚 Shipments
* 🔔 Notifications
* 🤖 AI Demand Forecasting

It showcases **event-driven architecture**, **scalable microservices**, and **AI-powered predictive analytics**.

---

# 🎯 Project Goal

The goal of this project is to demonstrate **enterprise-level backend architecture** used in companies such as:

* Global e-commerce platforms
* Warehouse automation systems
* Logistics & supply chain companies
* Retail inventory management platforms

This project highlights **modern backend engineering skills** including:

✔ Microservices architecture
✔ Event-driven systems
✔ Kafka messaging
✔ AI integration
✔ Cloud-native deployment

---

# 🏗️ System Architecture

The system follows a **distributed microservices architecture** where each service operates independently and communicates asynchronously through **Apache Kafka**.

### Core Components

🚪 **API Gateway**

* Routes client requests
* Handles authentication
* Central entry point for APIs

📡 **Kafka Event Bus**

* Event streaming backbone
* Enables asynchronous communication

⚙️ **Microservices Layer**
Independent Spring Boot services responsible for specific business domains.

🤖 **AI Forecasting Module**
Uses **Spring AI** to predict product demand.

🗄️ **Database Layer**
Each microservice maintains its own **PostgreSQL / MySQL database**.

📊 **Monitoring & Observability**

* Prometheus
* Grafana

☁️ **Containerized Infrastructure**

* Docker
* Kubernetes

---

# 🧩 Microservices Overview

## 📦 Order Service

Handles order creation and lifecycle management.

### Attributes

* `orderId`
* `customerId`
* `orderDate`
* `status`
* `items`

### Events

📨 `OrderCreated`
❌ `OrderCancelled`

---

## 📊 Inventory Service

Manages product stock across warehouses.

### Attributes

* `productId`
* `productName`
* `stockQuantity`
* `warehouseLocation`
* `reorderLevel`

### Events

⬇️ Consumes `OrderCreated`
⬆️ Publishes `StockUpdated`

---

## 💳 Payment Service

Handles order payment processing.

### Attributes

* `paymentId`
* `orderId`
* `amount`
* `paymentStatus`
* `transactionDate`

### Events

💰 Publishes `PaymentProcessed`

---

## 🚚 Shipment Service

Manages shipping and delivery tracking.

### Attributes

* `shipmentId`
* `orderId`
* `carrier`
* `trackingNumber`
* `status`

### Events

⬇️ Consumes `PaymentProcessed`
⬆️ Publishes `ShipmentUpdated`

---

## 🔔 Notification Service

Sends real-time notifications to users.

### Notification Types

📧 Email
📱 SMS
🔔 Push Notifications

Consumes events from all services and sends alerts to users.

---

## 🤖 AI Forecasting Service

Uses **Spring AI** to analyze historical orders and forecast demand.

### Attributes

* `forecastId`
* `productId`
* `predictedDemand`
* `confidenceScore`
* `forecastDate`

### Event

📊 `DemandForecastGenerated`

---

# 🔄 Event Driven Workflow

1️⃣ Customer places an order
➡ Order Service publishes **OrderCreated**

2️⃣ Inventory Service updates stock
➡ Publishes **StockUpdated**

3️⃣ Payment Service processes payment
➡ Publishes **PaymentProcessed**

4️⃣ Shipment Service creates shipment
➡ Publishes **ShipmentUpdated**

5️⃣ Notification Service informs the customer

6️⃣ AI Forecasting analyzes data and predicts future demand

---

# 🛠️ Technology Stack

### Backend

☕ Java 17
🌱 Spring Boot
🌐 Spring Cloud
📦 Spring Data JPA

### Messaging

📡 Apache Kafka

### AI Integration

🤖 Spring AI

### Database

🗄️ PostgreSQL / MySQL

### Infrastructure

🐳 Docker
☸️ Kubernetes

### Monitoring

📊 Prometheus
📈 Grafana

---

# 📂 Project Structure

```
smart-supply-chain-system
│
├── api-gateway
│
├── order-service
│
├── inventory-service
│
├── payment-service
│
├── shipment-service
│
├── notification-service
│
├── ai-forecasting-service
│
├── docker
│
├── kubernetes
│
└── README.md
```

---

# 📡 API Endpoints

### Order APIs

| Method | Endpoint           | Description       |
| ------ | ------------------ | ----------------- |
| POST   | `/api/orders`      | Create order      |
| GET    | `/api/orders/{id}` | Get order details |
| GET    | `/api/orders`      | List all orders   |
| DELETE | `/api/orders/{id}` | Cancel order      |

---

### Inventory APIs

| Method | Endpoint              | Description       |
| ------ | --------------------- | ----------------- |
| GET    | `/api/inventory`      | Get products      |
| GET    | `/api/inventory/{id}` | Get product stock |
| PUT    | `/api/inventory`      | Update inventory  |

---

### Payment APIs

| Method | Endpoint                  | Description     |
| ------ | ------------------------- | --------------- |
| POST   | `/api/payments`           | Process payment |
| GET    | `/api/payments/{orderId}` | Payment status  |

---

### Shipment APIs

| Method | Endpoint                   | Description     |
| ------ | -------------------------- | --------------- |
| POST   | `/api/shipments`           | Create shipment |
| GET    | `/api/shipments/{orderId}` | Track shipment  |

---

# 📡 Kafka Topics

| Topic         | Producer          | Consumer             |
| ------------- | ----------------- | -------------------- |
| orders        | Order Service     | Inventory, Payment   |
| inventory     | Inventory Service | Notification         |
| payments      | Payment Service   | Shipment             |
| shipments     | Shipment Service  | Notification         |
| notifications | All Services      | Notification Service |

---

# ⚡ Getting Started

## 1️⃣ Clone Repository

```
git clone https://github.com/yourusername/smart-supply-chain-system.git
```

---

## 2️⃣ Start Kafka using Docker

```
docker-compose up -d
```

---

## 3️⃣ Run Microservices

```
mvn spring-boot:run
```

---

## 4️⃣ Access APIs

Example:

```
POST /api/orders
GET /api/inventory
POST /api/payments
```

---

# 🐳 Docker Deployment

Build service image:

```
docker build -t order-service .
```

Run container:

```
docker run -p 8081:8081 order-service
```

---

# ☸️ Kubernetes Deployment

Deploy services:

```
kubectl apply -f k8s/order-service-deployment.yaml
kubectl apply -f k8s/inventory-service-deployment.yaml
```

Benefits:

✔ Auto Scaling
✔ Fault Tolerance
✔ High Availability

---

# 📊 Monitoring & Observability

Tools used for monitoring system health:

📈 **Prometheus** – Collects metrics
📊 **Grafana** – Visualizes dashboards

Metrics include:

* Order processing rate
* Payment success rate
* Inventory updates
* Kafka message throughput

---

# 🔐 Security

Security features implemented:

✔ JWT Authentication
✔ Secure APIs
✔ Role-based access control

Roles:

* ADMIN
* CUSTOMER
* WAREHOUSE_MANAGER

---

# 🧪 Testing

Testing tools used:

✔ JUnit
✔ Mockito
✔ Postman

Run tests:

```
mvn test
```

---

# 🔮 Future Enhancements

🚀 Redis caching for performance
🚀 Saga Pattern for distributed transactions
🚀 ElasticSearch analytics
🚀 Real-time supply chain dashboards

---

# 📚 What This Project Demonstrates

This project highlights expertise in:

✔ Distributed Systems
✔ Event-Driven Architecture
✔ Microservices Design
✔ Kafka Messaging
✔ AI Integration
✔ Cloud-Native Deployment

---

# 🤝 Contributing

Contributions are welcome.

Steps:

```
git checkout -b feature/new-feature
git commit -m "Add new feature"
git push origin feature/new-feature
```

Then create a **Pull Request**.

---

# 👨‍💻 Author

**Vicky Kumar Yadav**

💼 Java Backend Developer
⚙️ Spring Boot | Microservices | Kafka | Cloud

---

⭐ If you found this project helpful, **please give it a star on GitHub!**

---

