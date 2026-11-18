# Accounts REST API · Spring Boot

A small, cleanly layered REST service for managing user accounts, built with Spring Boot, Spring Data JPA and an in-memory H2 database.

![Java](https://img.shields.io/badge/Java_17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?logo=springboot&logoColor=white)
![Hibernate](https://img.shields.io/badge/JPA_/_Hibernate-59666C?logo=hibernate&logoColor=white)
![H2](https://img.shields.io/badge/H2_Database-1E5A96)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)

## Architecture

```
HTTP request
   │
   ▼
RestWelcomeController   /accounts/*      routing, status codes, request validation
   │
   ▼
AccountService                           business logic
   │
   ▼
AccountRepository (JpaRepository)        persistence, no hand-written SQL
   │
   ▼
H2 in-memory database                    table auto-created from the Account entity

GlobalExceptionHandler (@ControllerAdvice) turns exceptions into clean 404 / 500 responses
```

## Endpoints

| Method | Path | Body / params | Success | Errors |
|---|---|---|---|---|
| `GET` | `/accounts/{id}` | — | `200` account | `404` not found |
| `POST` | `/accounts/create` | account JSON | `201` created account | `500` |
| `PUT` | `/accounts/update` | account JSON with `id` | `200` updated account | `404` not found |
| `DELETE` | `/accounts/delete?id={id}` | — | `200` confirmation | `404` not found |

Passwords are accepted on create and update but are never included in responses.

## Run it

```bash
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`.

```bash
# create
curl -X POST localhost:8080/accounts/create \
  -H "Content-Type: application/json" \
  -d '{"username":"tamer","password":"secret","email":"tamer@example.com","status":"active"}'

# read
curl localhost:8080/accounts/1

# update
curl -X PUT localhost:8080/accounts/update \
  -H "Content-Type: application/json" \
  -d '{"id":1,"username":"tamer","password":"secret","email":"new@example.com","status":"active"}'

# delete
curl -X DELETE "localhost:8080/accounts/delete?id=1"
```

Browse the database at `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:testdb`, user `sa`, no password).

## Project layout

```
src/main/java/com/learn/demo/
├── DemoApplication.java          entry point
├── Account.java                  JPA entity
├── controller/RestWelcomeController.java
├── service/AccountService.java
├── dao/AccountRepository.java
└── exception/
    ├── GlobalExceptionHandler.java
    └── UserNotFoundException.java
```
