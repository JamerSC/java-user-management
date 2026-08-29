### Java Desktop Application
### User Management System
#### 8/17/2026

Architecture Overview
```
              Java Desktop Application
                         │
                         ▼
              ┌─────────────────────┐
              │     UI / GUI        │
              │ JavaFX / Swing      │
              └──────────┬──────────┘
                         │
                         ▼
              ┌─────────────────────┐
              │      Service        │
              │   Business Logic    │
              └──────────┬──────────┘
                         │
                         ▼
              ┌─────────────────────┐
              │        DAO          │
              │      JDBC / SQL     │
              └──────────┬──────────┘
                         │
                         ▼
              ┌─────────────────────┐
              │       MySQL         │
              └─────────────────────┘
```
#### User Management Desktop App

```
┌────────────────────────────────────────────┐
│              User Management               │
├────────────────────────────────────────────┤
│                                            │
│  Name:  [ Mary Public                 ]    │
│                                            │
│  Email: [ mary@example.com             ]   │
│                                            │
│          [ Save ] [ Update ] [ Delete ]    │
│                                            │
├────────────────────────────────────────────┤
│ ID │ Name          │ Email                 │
├────┼───────────────┼───────────────────────┤
│ 1  │ Mary          │ mary@example.com      │
│ 2  │ John          │ john@example.com      │
└────────────────────────────────────────────┘
```

### Java Core & Spring Boot (Java Framework) Comparison
```
Java Core                     Spring Boot
------------------------------------------------
Main                    →     Controller
UserService             →     Service
UserDAO                 →     Repository
JDBC                    →     JPA/Hibernate
DatabaseConnection      →     DataSource
User                    →     Entity
```

```
The purpose of the provided code and structure is to implement a User Management System using a layered architecture with JavaFX for the user interface, Java for the backend logic, and MySQL for data storage. Each layer has a specific responsibility:  
FXML (View Layer): Defines the graphical user interface (GUI) layout using XML. It separates the UI design from the application logic.  
Controller Layer: Handles user interactions (e.g., button clicks) and communicates with the service layer to perform actions like adding, updating, or deleting users.  
Service Layer: Contains the business logic of the application. It validates input and interacts with the DAO layer to perform database operations.  
DAO (Data Access Object) Layer: Manages database operations such as querying, inserting, updating, and deleting user data in the MySQL database.  
Database Connection Layer: Provides a reusable connection to the MySQL database.
```

Developer: JamerSC
Note: Recap, Refresh, & Practice Java Core