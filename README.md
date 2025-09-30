# � E-Commerce Microservices Platform

**Distributed E-Commerce System**

A modern microservices-based e-commerce platform built with Spring Boot, featuring service discovery, configuration management, and asynchronous communication.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.3-6db33f?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-6db33f?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-cloud)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-231f20?style=for-the-badge&logo=apache-kafka&logoColor=white)](https://kafka.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-47a248?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)

## 🌟 Features

- �️ **Product Catalog Management** - Comprehensive product inventory and catalog system
- � **Customer Management** - User registration, profiles, and authentication
- 📦 **Order Processing** - Complete order lifecycle management
- 💳 **Payment Processing** - Secure payment handling and transaction management
- � **Notification System** - Real-time notifications via email and messaging
- � **Asynchronous Communication** - Event-driven architecture with Kafka
- 🚪 **API Gateway** - Centralized routing and load balancing
- 🗃️ **Distributed Tracing** - Request tracking with Zipkin

## 🛠️ Tech Stack

**Backend:** Spring Boot 3.3.3, Spring Cloud 2023.0.3, Java 17  
**Databases:** PostgreSQL, MongoDB  
**Message Broker:** Apache Kafka  
**Service Discovery:** Netflix Eureka  
**API Gateway:** Spring Cloud Gateway  
**Configuration:** Spring Cloud Config Server  
**Monitoring:** Zipkin, Spring Boot Actuator

## �️ System Screenshots

<div align="center">
  <img src="./ecommerce/Capture d'écran 2025-09-30 111111.png" width="300" alt="E-Commerce Dashboard"/>
  <img src="./ecommerce/Capture d'écran 2025-09-30 111131.png" width="300" alt="Product Management"/>
  <img src="./ecommerce/Capture d'écran 2025-09-30 111143.png" width="300" alt="Order Processing"/>
</div>

## 🏗️ Microservices Architecture

The platform follows a microservices architecture pattern with the following components:

### Core Services

- **🛒 Product Service** - Product catalog, inventory management, and pricing
- **👤 Customer Service** - User management, authentication, and profiles
- **📋 Order Service** - Order processing, order history, and order status tracking
- **💰 Payment Service** - Payment processing, transaction management, and billing
- **📨 Notification Service** - Email notifications, SMS alerts, and push notifications

### Infrastructure Services

- **🚪 API Gateway** - Request routing, load balancing, and API composition
- **🗃️ Discovery Server** - Service registration and discovery (Eureka)
- **⚙️ Config Server** - Centralized configuration management
- **📊 Zipkin Server** - Distributed tracing and request monitoring

### Data Storage

- **🐘 PostgreSQL** - Relational data for orders, payments, and transactions
- **🍃 MongoDB** - Document storage for products, customers, and notifications

### Message Broker

- **📡 Apache Kafka** - Asynchronous communication between services

## 🚀 Quick Start

### Prerequisites

- Java 17+
- Maven 3.6+
- Docker & Docker Compose
- PostgreSQL
- MongoDB

### Environment Setup

1. **Clone the repository**

```bash
git clone https://github.com/Haythem532002/Microservice-Project.git
cd Microservice-Project
```

2. **Set up environment variables**
   Create a `.env` file in the root directory:

```env
# PostgreSQL Configuration
POSTGRES_USER=ecommerce_user
POSTGRES_PASSWORD=ecommerce_password

# PgAdmin Configuration
PGADMIN_DEFAULT_EMAIL=admin@ecommerce.com
PGADMIN_DEFAULT_PASSWORD=admin

# MongoDB Configuration
MONGO_INITDB_ROOT_USERNAME=mongo_admin
MONGO_INITDB_ROOT_PASSWORD=mongo_password
ME_CONFIG_MONGODB_ADMINUSERNAME=mongo_admin
ME_CONFIG_MONGODB_ADMINPASSWORD=mongo_password
ME_CONFIG_MONGODB_SERVER=mongodb
```

### Running with Docker Compose

```bash
# Start all infrastructure services
docker-compose up -d

# This will start:
# - PostgreSQL (port 5432) + PgAdmin (port 5050)
# - MongoDB (port 64000) + Mongo Express (port 8081)
# - Kafka + Zookeeper
# - MailDev (port 1080)
```

### Running Microservices

```bash
# Start services in the following order:

# 1. Config Server
cd services/config-server
mvn spring-boot:run

# 2. Discovery Server
cd ../discovery
mvn spring-boot:run

# 3. API Gateway
cd ../gateway
mvn spring-boot:run

# 4. Business Services (can be started in parallel)
cd ../customer && mvn spring-boot:run &
cd ../product && mvn spring-boot:run &
cd ../order && mvn spring-boot:run &
cd ../payment && mvn spring-boot:run &
cd ../notification && mvn spring-boot:run &
```

### Service Endpoints

- **🗃️ Eureka Discovery Server**: http://localhost:8761
- **🚪 API Gateway**: http://localhost:8080
- **🛒 Product Service**: http://localhost:8050
- **👤 Customer Service**: http://localhost:8060
- **📋 Order Service**: http://localhost:8070
- **💰 Payment Service**: http://localhost:8090
- **📨 Notification Service**: http://localhost:8040
- **📊 Zipkin Tracing**: http://localhost:9411
- **📧 MailDev**: http://localhost:1080

## 📁 Project Structure

```
Microservice-Project/
├── docker-compose.yml          # Infrastructure services configuration
├── services/                   # Microservices directory
│   ├── config-server/         # Spring Cloud Config Server
│   │   ├── src/main/
│   │   │   ├── java/          # Configuration server implementation
│   │   │   └── resources/     # Configuration files
│   │   └── pom.xml
│   ├── discovery/             # Netflix Eureka Discovery Server
│   │   ├── src/main/
│   │   │   ├── java/          # Service registry implementation
│   │   │   └── resources/     # Eureka configuration
│   │   └── pom.xml
│   ├── gateway/               # Spring Cloud Gateway
│   │   ├── src/main/
│   │   │   ├── java/          # API Gateway routing logic
│   │   │   └── resources/     # Gateway configuration
│   │   └── pom.xml
│   ├── customer/              # Customer Management Service
│   │   ├── src/main/
│   │   │   ├── java/          # Customer domain logic
│   │   │   └── resources/     # Service configuration
│   │   └── pom.xml
│   ├── product/               # Product Catalog Service
│   │   ├── src/main/
│   │   │   ├── java/          # Product domain logic
│   │   │   └── resources/     # Service configuration
│   │   └── pom.xml
│   ├── order/                 # Order Processing Service
│   │   ├── src/main/
│   │   │   ├── java/          # Order domain logic
│   │   │   └── resources/     # Service configuration
│   │   └── pom.xml
│   ├── payment/               # Payment Processing Service
│   │   ├── src/main/
│   │   │   ├── java/          # Payment domain logic
│   │   │   └── resources/     # Service configuration
│   │   └── pom.xml
│   └── notification/          # Notification Service
│       ├── src/main/
│       │   ├── java/          # Notification logic
│       │   └── resources/     # Email templates & config
│       └── pom.xml
└── ecommerce/                 # Documentation and screenshots
    ├── Capture d'écran 2025-09-30 111111.png
    ├── Capture d'écran 2025-09-30 111131.png
    └── Capture d'écran 2025-09-30 111143.png
```

## 🔧 Configuration

Each microservice is configured through the Config Server, providing centralized configuration management. Key configuration aspects:

- **Service Discovery**: All services register with Eureka
- **Database Configuration**: PostgreSQL for transactional data, MongoDB for documents
- **Kafka Integration**: Event-driven communication between services
- **Security**: JWT-based authentication and authorization
- **Monitoring**: Actuator endpoints for health checks and metrics

## 📡 Inter-Service Communication

The platform uses both synchronous and asynchronous communication patterns:

### Synchronous Communication

- **API Gateway** routes external requests to appropriate services
- **Service-to-Service** calls for immediate data requirements

### Asynchronous Communication (Kafka)

- **Order Events**: Order creation, status updates, cancellations
- **Payment Events**: Payment processing results, refunds
- **Notification Events**: Email triggers, SMS notifications
- **Inventory Events**: Stock updates, product availability changes

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## � Monitoring and Observability

- **🔍 Distributed Tracing**: Zipkin for request flow visualization
- **📈 Metrics Collection**: Spring Boot Actuator endpoints
- **🏥 Health Checks**: Service health monitoring
- **📝 Logging**: Centralized logging with correlation IDs

## 🚀 Deployment

The application can be deployed using:

- **Docker Compose** for local development
- **Kubernetes** for production environments
- **Cloud Platforms** (AWS, Azure, GCP) with container orchestration

## �📄 License

This project is licensed under the MIT License.

---

**Built with ❤️ using Spring Boot & Microservices Architecture**
