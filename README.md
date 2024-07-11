**ProgramMe**

A backend application for "ProgramMe," a platform where 
programmers can showcase their expertise and services, and 
customers can hire them for programming-related tasks. 
The application will be built using Java, Spring Boot, 
and a MySQL database, following REST API best practices.

--------

<img width="181" alt="Screenshot 2024-07-10 at 8 32 53 PM" src="https://github.com/IsmailAbousalem/ProgramMe/assets/100754446/95f84c37-a9c2-4648-9e27-0d915957a477">

--------

**Setup**

Development Environment:

- IntelliJ IDEA
- Java 17
- Maven
- Spring Boot 3.3.1 (Spring Web, Spring Data JPA, MySQL Driver, and Spring Security (coming soon))
- MySQL
- DBeaver
- JPA/Hibernate
- Postman

Steps to Set Up:

1. Clone the repository from GitHub.
2. In application.properties file, configure your MySQL database information:
```application properties
spring.datasource.url=jdbc:mysql://localhost:3306/ProgramMe?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username= (input your SQL username here, typically "root")

spring.datasource.password= (input your SQL password here)

... rest of application properties
```
3. Build and run the Spring Boot application.
4. Refresh DBeaver SQL database and check to see if the database/tables have been created.
5. Add some seed data to the tables to test endpoints.
4. Use Postman or any API client to test the endpoints.

--------

**Technologies Used**

Backend:
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- JUnit for testing

Tools:
- IntelliJ IDEA
- GitHub for version control

--------

**Controllers and Routes Structure**

*CustomerController:*

- GET /customers
- POST /customers
- PUT /customers/{id}
- DELETE /customers/{id}

*ProgrammerController:*

- GET /programmers
- POST /programmers
- PUT /programmers/{id}
- DELETE /programmers/{id}

*PostController:*

- GET /posts
- POST /posts
- PUT /posts/{id}
- DELETE /posts/{id}

--------

**Extra Links**

- Google Slides Presentation: https://docs.google.com/presentation/d/1UUmu5plor5oJhyJZKAHubgjhPZFoMX_h7Rxs_jRRpC0/edit?usp=sharing
- LucidChart (For UML Diagram)

--------

**Future Work**

- Implement authentication and authorization
- Add more detailed search and filter functionalities
- Improve error handling and validation
- Add frontend for better user interaction

--------

**Resources**

- Spring Boot Documentation
- Spring Data JPA Documentation
- MySQL Documentation
- JUnit Documentation

--------

**Developer**

- Ismail Abousalem