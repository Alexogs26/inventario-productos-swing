# 📦 Spring Boot Inventory System

A console-based application to manage a product inventory. This project represents a migration from raw JDBC to the **Spring Boot** ecosystem, implementing professional backend practices like Layered Architecture, Inversion of Control (IoC), and ORM with Hibernate.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![MariaDB](https://img.shields.io/badge/Database-MariaDB-blue)

## 🛠️ Tech Stack

* **Language:** Java 21 (LTS).
* **Framework:** Spring Boot (Core, Context, DI).
* **Data Persistence:** Spring Data JPA (Hibernate implementation).
* **Database:** MariaDB (using native MariaDB Java Client).
* **Boilerplate Reduction:** Project Lombok (`@Data`, `@Builder`, `@RequiredArgsConstructor`).
* **Build Tool:** Maven.
* **Version Control:** Git & GitHub (GitHub Flow).

## 🚀 Key Features

* **CRUD Operations:** Full management of products (Create, Read, Update, Delete).
* **Search:** Find products efficiently by SKU (ID).
* **Architecture:** Clean separation of concerns (Controller/Main -> Service -> Repository -> Database).
* **Safety:** Implements `@Transactional` for data integrity.
* **Optimization:** Uses *HikariCP* for connection pooling and *Dirty Checking* for efficient updates.

## ⚙️ Configuration

The project is configured to use **Environment Variables** for database credentials to ensure security (12-Factor App methodology).

**Required Variables:**
* `DB_URL`: `jdbc:mariadb://localhost:3306/your_database_name`
* `DB_USER`: `your_username`
* `DB_PASSWORD`: `your_password`

## 👤 Author

**Oswaldo Alexis Gómez Sánchez**
* *Software Engineering Student @ UNITEC*
* *Backend Developer in training*

---
*Built with ❤️ using IntelliJ IDEA & Fedora Linux.*