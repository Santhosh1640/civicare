# 🧹 Civicare  
### Event-Driven Civic Issue Reporting System

**Civicare** is an **event-driven microservices-based backend system** that enables citizens to report public cleanliness and civic issues (garbage dumping, unhygienic areas, etc.) and automatically routes them to the appropriate authorities for action.

This project demonstrates **real-world backend engineering practices** such as **Kafka-based asynchronous communication**, **idempotency**, and **loosely coupled microservices**.

---

## ✨ Features

- ✅ REST APIs for civic issue reporting  
- ✅ Event-driven architecture using **Apache Kafka**  
- ✅ **Idempotent** report creation to prevent duplicates  
- ✅ Duplicate detection using geo-location & time window  
- ✅ Independent microservices with separate databases  
- ✅ Docker-based local infrastructure  

---

## 🏗️ Architecture Overview

Client
↓
Report Service
↓
PostgreSQL (reports)
↓
Kafka Topic: report-created
↓
Assignment Service
↓
PostgreSQL (assignments)

---


---

## 🧩 Services

### 🟦 Report Service
- Exposes REST APIs for citizens to report civic issues
- Persists reports in **PostgreSQL**
- Implements **idempotency** to avoid duplicate submissions
- Performs basic duplicate detection (geo + time based)
- Publishes a **`report-created`** event to Kafka

---

### 🟩 Assignment Service
- Consumes **`report-created`** Kafka events
- Assigns reports to a ward / zone / worker (simple logic)
- Stores assignment details in its own **PostgreSQL** database
- Completely decoupled from Report Service

---

## 🔁 Event-Driven Flow

- Services communicate **asynchronously**
- No direct REST calls between services
- Loose coupling via Kafka
- Defensive event consumption

---

## 🧠 Key Engineering Concepts

- **Microservices architecture**
- **Event-driven system design**
- Apache Kafka producers & consumers
- Safe JSON deserialization across services
- Idempotency & duplicate handling
- Independent databases per service
- Docker & Docker Compose
- Clean separation of responsibilities

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Kafka**
- **Apache Kafka & Zookeeper**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**

---

## 📂 Repository Structure

civicare/
│
├── report-service/
│ ├── src/
│ └── pom.xml
│
├── assignment-service/
│ ├── src/
│ └── pom.xml
│
├── docker-compose.yml
├── README.md
└── .gitignore

---


---

## ▶️ Running Locally

1. Start Kafka & Zookeeper:
   ```bash
   docker compose up -d
2. Start Report Service
3. Start Assignment Service
4. Send a report request to Report Service
5. Verify:
   a. Report saved in Report DB
   b. Kafka event published
   c. Assignment created in Assignment DB

---

## 🚀 Future Enhancements

1. Assignment idempotency
2. Retry & Dead Letter Queue (DLQ)
3. Event versioning
4. Notification Service
5. Worker prioritization logic
6. Monitoring & observability






