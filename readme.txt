# Employee & Department Microservices

## 1. Project Overview

The Employee & Department Microservices project is a Java Spring Boot application developed using a microservices architecture.

The project consists of two independent microservices:

- **Employee Service:** Manages employee information and employee-related operations.
- **Department Service:** Manages department information and department-related operations.

Both services use MySQL databases for data persistence. The Employee Service communicates with the Department Service through REST APIs to retrieve department information associated with an employee.

## 2. Technologies Used

- Java 17
- Spring Boot 4.1.1
- Spring Data JPA
- Hibernate
- MySQL 8.0
- Maven
- REST APIs
- Jakarta Bean Validation
- DTO (Data Transfer Object)
- Swagger / OpenAPI
- Postman
- Git and GitHub
- Eclipse Spring Tool Suite (STS)

## 3. Project Architecture

The application contains two independent microservices.

| Microservice | Port | Database |
|---|---:|---|
| Department Service | 8081 | department_db |
| Employee Service | 8082 | employee_db |

### Department Service

Responsible for:
- Creating departments.
- Retrieving all departments.
- Retrieving departments by ID.
- Updating department information.
- Deleting departments.

### Employee Service

Responsible for:
- Creating employees.
- Retrieving all employees.
- Retrieving employees by ID.
- Updating employee information.
- Deleting employees.
- Retrieving employee details along with department information.

## 4. Project Folder Structure

```text
Employee-Department-Microservices
│
├── department-service
│   └── src/main/java/com/example/department
│       ├── controller
│       │   └── DepartmentController.java
│       ├── dto
│       │   ├── DepartmentRequestDTO.java
│       │   └── DepartmentResponseDTO.java
│       ├── entity
│       │   └── Department.java
│       ├── repository
│       │   └── DepartmentRepository.java
│       └── service
│           └── DepartmentService.java
│
├── employee-service
│   └── src/main/java/com/example/employee
│       ├── config
│       │   └── RestClientConfig.java
│       ├── controller
│       │   └── EmployeeController.java
│       ├── dto
│       │   ├── DepartmentDTO.java
│       │   ├── EmployeeRequestDTO.java
│       │   ├── EmployeeResponseDTO.java
│       │   └── EmployeeWithDepartmentDTO.java
│       ├── entity
│       │   └── Employee.java
│       ├── repository
│       │   └── EmployeeRepository.java
│       └── service
│           └── EmployeeService.java
│
├── README.md
└── .gitignore
```

## 5. Database Configuration

Create the following databases in MySQL:

```sql
CREATE DATABASE department_db;

CREATE DATABASE employee_db;
```

Configure the database connections in the respective `application.properties` files.

### Department Service

```properties
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/department_db
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Employee Service

```properties
server.port=8082

spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password. Do not commit actual database passwords to GitHub.

## 6. REST API Endpoints

### Department Service

Base URL:

`http://localhost:8081/api/departments`

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/departments | Add a department |
| GET | /api/departments | Get all departments |
| GET | /api/departments/{id} | Get department by ID |
| PUT | /api/departments/{id} | Update department |
| DELETE | /api/departments/{id} | Delete department |

### Sample Department Request

```json
{
    "departmentName": "Information Technology",
    "description": "IT Department"
}
```

### Employee Service

Base URL:

`http://localhost:8082/api/employees`

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/employees | Add an employee |
| GET | /api/employees | Get all employees |
| GET | /api/employees/{id} | Get employee by ID |
| PUT | /api/employees/{id} | Update employee |
| DELETE | /api/employees/{id} | Delete employee |
| GET | /api/employees/{id}/details | Get employee with department details |

### Sample Employee Request

```json
{
    "employeeName": "Rahul Sharma",
    "email": "rahul@gmail.com",
    "designation": "Java Developer",
    "salary": 45000,
    "departmentId": 1
}
```

## 7. Inter-Service Communication

The Employee Service communicates with the Department Service using Spring RestClient.

When the following endpoint is called:

`GET http://localhost:8082/api/employees/1/details`

The Employee Service:

1. Retrieves employee information from the employee database.
2. Gets the employee's department ID.
3. Calls the Department Service REST API.
4. Retrieves the corresponding department information.
5. Returns employee and department information in a combined DTO response.

## 8. DTO Implementation

DTOs are used to transfer data between the client, services, and microservices.

### Employee DTOs
- EmployeeRequestDTO
- EmployeeResponseDTO
- EmployeeWithDepartmentDTO
- DepartmentDTO

### Department DTOs
- DepartmentRequestDTO
- DepartmentResponseDTO

DTO mapping separates API request and response structures from database entities.

## 9. Input Validation

Jakarta Bean Validation is implemented to validate incoming API requests.

Employee validations include:
- Employee name cannot be blank.
- Email must be valid.
- Designation cannot be blank.
- Salary cannot be negative.
- Department ID must be greater than zero.

Department validations include:
- Department name cannot be blank.
- Department name length is restricted.
- Description length is restricted.

Invalid requests are rejected with HTTP 400 Bad Request.

## 10. Swagger API Documentation

Swagger UI is integrated into both microservices using Springdoc OpenAPI.

### Department Service Swagger

http://localhost:8081/swagger-ui/index.html

### Employee Service Swagger

http://localhost:8082/swagger-ui/index.html

Swagger provides an interactive interface to view and execute REST API endpoints.

## 11. Running the Project

Follow these steps to run the application:

1. Install Java 17, MySQL 8.0, and Eclipse STS.
2. Create the two MySQL databases.
3. Import both Maven projects into Eclipse STS.
4. Configure the database credentials in both `application.properties` files.
5. Update both Maven projects.
6. Start the Department Service on port 8081.
7. Start the Employee Service on port 8082.
8. Use Postman to test the REST APIs.
9. Open the Swagger URLs to explore the API documentation.

**Important:** Start Department Service before Employee Service because Employee Service communicates with it.

## 12. API Testing

Postman is used to test the REST APIs.

The following operations are covered:

- POST – Create employee and department records.
- GET – Retrieve employee and department records.
- PUT – Update existing records.
- DELETE – Delete records.
- Inter-service communication – Retrieve employee details with department information.
- Input validation – Verify rejection of invalid requests.

## 13. HTTP Status Codes

| Status Code | Description |
|---|---|
| 200 OK | Request successful |
| 201 Created | Resource created successfully |
| 204 No Content | Resource deleted successfully |
| 400 Bad Request | Invalid request data |
| 404 Not Found | Requested resource not found |

## 14. GitHub Repository

Project Repository:

https://github.com/AdityaaKurle/taskED.git

The repository contains the source code for both microservices and project documentation.

## 15. Conclusion

The Employee & Department Microservices project demonstrates the implementation of a microservices-based application using Java and Spring Boot.

It covers MySQL database integration, RESTful CRUD operations, inter-service communication, DTO mapping, input validation, Swagger documentation, and API testing using Postman.

The project provides practical experience in developing independent Spring Boot microservices and integrating them through REST APIs.