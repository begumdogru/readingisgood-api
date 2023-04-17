# Project Name: ReadingIsGoodAPI

## Design:

The ReadingIsGoodAPI is a RESTful web service for managing orders and books in a bookstore.
It follows a layered architecture design pattern with separate layers for controller, service, repository, and model.
It uses Spring Boot framework for building the API, with Java as the programming language.
It uses H2 in-memory database for storing order and book data.

## Tech Stack:

Java 11
Spring Boot 2.5.5
Spring MVC for handling RESTful API endpoints
Spring Data JPA for data access and persistence
H2 in-memory database for storing data
Maven for dependency management

## Project Structure:

com.example.readingisgoodapi: Root package of the project
controller: Contains RESTful API controllers
service: Contains business logic and service implementations
repository: Contains interfaces for data access and repository implementations
model: Contains POJO classes for representing entities
resources: Contains application properties and database schema script

## Assumptions:

The project assumes that the bookstore only deals with book orders, and not with other types of products.
The project assumes that authentication and authorization mechanisms are handled by an external system, and are not implemented within the API.
The project assumes that input validation and error handling are handled by appropriate exception handling mechanisms, but does not go into detail about the specifics of error handling.


## How to Start the Project:

Clone the project from the source code repository.
Make sure Java JDK 11 and Maven are installed on your machine.
Open the project in your preferred Java IDE.
Update the application properties in the resources directory, if needed (e.g., database connection properties).
Build the project using Maven.
Run the project as a Spring Boot application.
The API will be accessible at http://localhost:8080 by default, and you can use an API client (e.g., Postman) to test the endpoints.


## Credentials:

The project does not include any authentication or authorization mechanisms, so no credentials are required for accessing the API endpoints.
