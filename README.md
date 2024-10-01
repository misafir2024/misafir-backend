# Misafir Backend

Misafir Backend is a Spring Boot (Java 21) REST API designed for the Misafir application. MongoDB is set up via Docker, and you can manage the database using MongoDB Compass.

## Getting Started

### Prerequisites
- [Java 21](https://jdk.java.net/21/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [MongoDB Compass](https://www.mongodb.com/products/compass) (optional for managing MongoDB)

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/misafir2024/misafir-backend.git
    cd misafir-backend
    ```
2. Build the project:
    ```bash
    mvn clean install
    ```

### Running MongoDB with Docker
1. Ensure Docker is installed and running on your machine.
2. Set up MongoDB using Docker Compose:
    ```bash
    docker-compose up -d
    ```
   This will start a MongoDB container and expose it on `mongodb://localhost:27017`.

### Running the Spring Boot Application
1. Run the Spring Boot app on your local machine:
    ```bash
    mvn spring-boot:run
    ```
2. The backend will be accessible at [http://localhost:8080](http://localhost:8080).

### MongoDB Configuration
In your `application.properties`, configure the MongoDB connection string to point to the MongoDB instance running in Docker:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017
