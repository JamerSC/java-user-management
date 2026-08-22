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

Developer: JamerSC
Note: Recap, Refresh, & Practice Java Core