
# 🛠️ Spring Boot CRUD API with Docker & MySQL

This project is a simple **Spring Boot 3** REST API built using **Java 17**, with a MySQL database running via Docker. It supports basic CRUD operations on a `User` entity.

---

## 🚀 Features

- CRUD APIs: Create, Read, Update, Delete users
- Uses **Lombok** to reduce boilerplate
- Auto timestamps: `createdAt`, `updatedAt`
- Dockerized Spring Boot App & MySQL DB
- DTO mapping with builder pattern
- Clean service & repository layer structure

---

## 🧰 Tech Stack

- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- MySQL 8 (via Docker)
- Maven
- Lombok
- Docker & Docker Compose

---

## 📁 Project Structure

```
.
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── app/
│       │           └── demo/
│       │               ├── controller/
│       │               ├── dto/
│       │               ├── entity/
│       │               ├── repository/
│       │               ├── service/
│       │               └── DemoApplication.java
│       └── resources/
│           └── application.properties
```

---

## ⚙️ Run Instructions

### 1️⃣ Build the Project

```bash
mvn clean package -DskipTests
```

### 2️⃣ Start Services Using Docker

```bash
docker-compose up --build
```

📌 This builds your Spring Boot JAR and starts both:
- `mysql-docker` (MySQL container)
- `springboot-app` (your app container)

---

## 🌐 API Endpoints

| Method | Endpoint        | Description        |
|--------|------------------|--------------------|
| GET    | `/users`         | Get all users      |
| GET    | `/users/{id}`    | Get user by ID     |
| POST   | `/users`         | Create user        |
| PUT    | `/users/{id}`    | Update user        |
| DELETE | `/users/{id}`    | Delete user        |

### ✅ Sample JSON (POST/PUT)

```json
{
  "name": "Naveen",
  "age": 28,
  "salary": 60000,
  "role": "Backend Developer"
}
```

---

## 🐬 MySQL Database Configuration

MySQL runs in a container using these environment variables (from `docker-compose.yml`):

```yaml
environment:
  MYSQL_ROOT_PASSWORD: rootpassword
  MYSQL_DATABASE: testdb
  TZ: Asia/Kolkata
```

### Spring Configuration (`application.properties`)

```properties
spring.datasource.url=jdbc:mysql://mysql:3306/testdb
spring.datasource.username=root
spring.datasource.password=rootpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

---

## 🛡️ Lombok Annotations Used

| Annotation       | Purpose                                      |
|------------------|----------------------------------------------|
| `@Data`          | Getters, Setters, toString, equals, hashCode |
| `@Builder`       | Builds objects in a readable way             |
| `@AllArgsConstructor` | Constructor with all fields             |
| `@NoArgsConstructor`  | Default constructor                     |
| `@RequiredArgsConstructor` | For constructor injection         |

---

## 🐙 Git & Version Control

Add this to `.gitignore` to avoid committing unnecessary files:

```
/target/
*.log
*.class
.factorypath
.idea/
*.iml
.DS_Store
.env
.vscode/
```

---

## 📦 Build JAR & Deploy

You can build the jar file with:

```bash
mvn clean package -DskipTests
```

Run locally:

```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

---

## ✍️ Author

**Naveen K**  
Full Stack Developer  
📧 GitHub: Naveen3873

---
