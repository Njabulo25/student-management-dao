# Student Management DAO (JDBC)

This repository contains a simple Java application that demonstrates the **Data Access Object (DAO) pattern** using **JDBC** to manage student records in a relational database.

The project is intentionally minimal and focused, designed to clearly show how database access logic can be separated from business logic and application flow.

---

## Project Purpose

The goal of this project is to demonstrate:

- Practical use of the DAO design pattern
- JDBC-based CRUD operations
- Clean separation of concerns
- Safe database interaction using prepared statements
- Basic layered architecture in Java

This project prioritizes clarity and correctness over complexity.

---

## Technologies Used

- Java SE
- JDBC
- Apache Derby (client-server mode)
- Swing (`JOptionPane`) for basic user interaction

---

## Database Schema

The application expects the following table:
sql

CREATE TABLE StudentTable (
    StudentNumber INT PRIMARY KEY,
    Name VARCHAR(50),
    Surname VARCHAR(50)
);

