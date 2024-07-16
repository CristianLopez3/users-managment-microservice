
# TI Users Management System

## Overview
This project is a Spring Boot-based application designed to manage user information for a technology company. It provides RESTful APIs to create, retrieve, and manage company-related data, including company details and user profiles.



### Simple configuration to run with docker-compose
Execute the next command to run the application with docker-compose
```
docker-compose up -d
```

## Technologies Used
- Java
- Spring Boot
- Maven
- MySQL
- Spring Data JPA
- Lombok
- Cucumber for testing

## Features
- Create and manage company information.
- Pagination and sorting of company listings.
- Integration with MySQL database for data persistence.
- Use of Project Lombok to reduce boilerplate code.
- Comprehensive test coverage with Cucumber.

## Getting Started

### Prerequisites
- JDK 11 or later
- Maven 3.6 or later
- MySQL Server

### Setting Up the Database
1. Create a new MySQL database named `ti_users`.
2. Update `src/main/resources/application.yml` with your MySQL user and password.

### Running the Application
1. Clone the repository to your local machine.
2. Navigate to the project directory and run the following command to build the project:
```
mvn clean install
```

3. To start the application, run:
```
mvn spring-boot:run
```
4. The application will be accessible at `http://localhost:8080`.

## Detalles de los Endpoints

Below are the details of the available endpoints:

### Departamentos

- **POST** `/api/v1/departments` - Save a new department.
    - **Body**:
      ```json
      {
        "name": "Finanzas",
        "description": "departamento encargado de las finanzas globalmente.",
        "companyId": 2
      }
      ```

- **PUT** `/api/v1/departments/{id}` - Update a existing department.
    - **Body**:
      ```json
      {
        "name": "Finanzas Comerciales",
        "description": "departamento encargado de las finanzas globalmente."
      }
      ```

### Users

- **POST** `/api/v1/users` - Create a new user.
    - **Body**:
      ```json
      {
        "name": "Chris",
        "lastname": "Brown",
        "address": "Mid Street",
        "position": "Talent Manager",
        "telephone": "3231234212",
        "residenceCity": "New York",
        "state": true,
        "companyId": 1,
        "departmentId": 1
      }
      ```

- **GET** `/api/v1/users?companyId=2` - Get users by company ID.

- **DELETE** `/api/v1/users/{id}` - Delete a determinate user.

### Compañías

- **POST** `/api/v1/companies` - Crea una nueva compañía.
    - **Body**:
      ```json
      {
        "name": "Fake Name",
        "address": "Calle 28",
        "operationCity": "Fake City"
      }
      ```

- **GET** `/api/v1/companies?page=0` - Get a list of companies with pagination.

## Testing
The project uses Cucumber for integration testing. To run the tests, execute:
```
mvn test
```

## Contributing
Contributions are welcome. Please open an issue to discuss your idea or submit a pull request.



