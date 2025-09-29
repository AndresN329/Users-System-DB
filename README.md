# 📌 Users-System-DB

A user management system (**CRUD**) developed in **Java** with a connection to a relational database. The project allows users to register, list, update, and delete (**Coder entity**) users, applying good **OOP** practices, **layering**, and reusable utilities.

---

## 🏗️ Project Structure
```
src/
├── controller/   # Controllers that coordinate business logic
│   └── CoderController.java
├── database/     # Generic database connections and operations
│   ├── ConfigDB.java
│   └── CRUD.java
├── entity/       # Entities or POJOs
│   └── Coder.java
├── model/        # Business logic and persistence
│   └── CoderModel.java
├── ui/           # User interface (menus)
│   └── MainMenu.java
├── utils/        # Utilities (inputs, JOptionPane, etc.)
│   ├── InputRequester.java
│   └── JOptionPaneUtils.java
└── Main.java     # Application entry point
```
---
## ⚙️ Technologies Used

* **Java 17+**
* **JDBC** (MySQL Connector)
* **OOP** (Classes, abstraction, encapsulation, inheritance)
* **IntelliJ IDEA** as the recommended IDE
* **MySQL** as the database engine

---

## ▶️ How to Run the Project

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/AndresN329/Users-System-DB.git
    ```

2.  **Configure your MySQL database:**

    ```sql
    CREATE DATABASE users_system;
    USE users_system;

    CREATE TABLE coders (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        age INT NOT NULL,
        email VARCHAR(100) UNIQUE NOT NULL
    );
    ```

3.  **Set the connection credentials in `ConfigDB.java`:**

    ```java
    String url = "jdbc:mysql://localhost:3306/users_system";
    String user = "root";
    String password = "your_password";
    ```

4.  **Run the program from the `Main.java` class.**

---

## 📌 Main Features

* 👤 **Register User** (Coder)
* 📋 **List All Users**
* 🔎 **Search User by ID**
* ✏️ **Update User Data**
* ❌ **Delete User**
* 🖥️ **Interactive Menu** with `JOptionPane`

---

## 📂 Flow Example

The system starts with the `MainMenu`.

The user selects an option (Register, List, Update, Delete).

The corresponding action is executed in the database via the `CoderModel`.

The result is displayed using dialog boxes (`JOptionPane`).

---

## 🚀 Upcoming Enhancements

* More robust validation handling.
* Implement a service layer for more complex business rules.
* Add unit tests.
