# ISA (Internet Architecture Services) - EmployeeManager Project

## Introduction
This repository contains my project developed during the Internet Services Architecture course laboratories. The project evolved through several stages, from basic Java SE implementation to a full microservices architecture with containerized deployment.

## Project Structure
The project is organized into separate branches for each laboratory session, with each branch building upon the previous work:

- [Laboratory 1](../../tree/lab1) - Java SE Implementation
  - Entity classes implementation
  - Stream API operations
  - Serialization
  - Parallel processing
  - [Laboratory Instructions](docs/Laboratory_1.pdf)

- [Laboratory 2](../../tree/lab2) - Spring Framework Basics
  - JPA entity mappings
  - Repository and service layers
  - H2 database integration
  - [Laboratory Instructions](docs/Laboratory_2.pdf)

- [Laboratory 3](../../tree/lab3) - Spring MVC
  - REST API implementation
  - DTO pattern
  - CRUD operations
  - [Laboratory Instructions](docs/Laboratory_3.pdf)

- [Laboratory 4](../../tree/lab4) - Microservices
  - Service decomposition
  - Inter-service communication
  - API Gateway implementation
  - [Laboratory Instructions](docs/Laboratory_4.pdf)

- [Laboratory 5](../../tree/lab5) - Angular Frontend
  - Components implementation
  - Routing setup
  - CRUD operations UI
  - [Laboratory Instructions](docs/Laboratory_5.pdf)

- [Laboratory 6](../../tree/lab6) - Docker & Docker Compose
  - Container configuration
  - Multi-container setup
  - Database containerization
  - [Laboratory Instructions](docs/Laboratory_6.pdf)

## Technologies Used
- Java SE
- Spring Framework
- Spring Boot
- Spring Cloud
- Angular
- Docker
- Docker Compose
- H2 Database
- REST APIs
- NGINX

## Prerequisites
- Java 17 or higher
- Node.js and npm
- Docker and Docker Compose
- Maven

## Getting Started

### Prerequisites
- Docker and Docker Compose installed
- Git (to clone the repository)

 ###Quick Start

1. Clone the repository:
```bash
git clone <repository-url>
cd <project-directory>
```

2. Build Docker images for each service:
```bash
# Build Gateway Service
cd gateway-service
docker build -t gateway-image .

# Build Company Service
cd ../company-service
docker build -t company-image .

# Build Employee Service
cd ../employee-service
docker build -t employee-image .

# Build Frontend
cd ../frontend
docker build -t frontend-image .
```

3. Start the application:
```bash
# From the root directory
docker-compose up
```
