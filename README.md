# Spring Boot API boilerplate

[![MIT Licence](https://img.shields.io/npm/l/treblle)](LICENSE.md)

An awesome boilerplate for your next Spring Boot based API. It's only goal is to simply **kick-start your API development** and provide you with some of the best practices when building amazing and scalable REST APIs 🔥

## Features

### REST API Best Practices
We baked in all the best REST API practices in terms of structuring your API, naming conventions, HTTP methods, responses and optimizations
	
### Concrete examples
We all like to learn by examples and that's why  the boilerplate comes with a concrete example that include everything from folder structure, repositories, services and controllers

### OpenAPI/Swagger support
The API documentation is generated automatically on application start using the awesome SpringDoc OpenAPI library

### Entities and migrations
We built two tables Users and Posts and defined everything you might need on a database and entity level. Schema migrations are handled by Liquibase. 

### Spawn a new user quickly
New users can be added quickly by using the public endpoint:

    POST /api/v1/users

### Real world authentication
We provide examples of using both HTTP Basic and JWT Bearer authentication

### Treblle built-in
We added an awesome Spring Boot dependency called Treblle. Out of the box Treblle gives you: real-time API monitoring, automatically generated and updated documentation, error tracking and logging, API analytics, quality scoring and much more. To get started with Treblle visit [treblle.com](https://treblle.com) for more information.

Treblle makes it super easy to understand what’s going on with your APIs and the apps that use them. Just by adding Treblle to your API out of the box, you get:

- Real-time API monitoring and logging
- Auto-generated API docs with OAS support
- API analytics
- Quality scoring
- One-click testing
- API management on the go
- and more...

## Requirements

- Spring Boot 2.7.4
- Java 8+

## Getting started

1. Configure your Treblle API key and Project ID in the **application.properties** file
2. Run the following command in the terminal
   
        ./mvnw spring-boot:run

3. Access the Swagger UI at [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
   
4. Create a user by calling (check the Swagger UI)
   
        POST /api/v1/users

5. Authenticate using HTTP Basic using the users email and password by calling 

        POST /api/v1/iam/authenticate

6. Use the resulting JWT token to access all other resources


## Thank you

The boilerplate was built by [IvoMajic](https://github.com/IvoMajic) and sponsored by [Treblle](https://treblle.com). We would love to have you as our contributor so please feel free to make pull requests and help us make the best API boilerplate on Github 💪🏻

## The 10 REST Commandments E-book

![# The 10 REST Commandments](https://treblle-assets.s3.amazonaws.com/api-book.jpg)Grab a free copy of The 10 REST Commandments e-book and learn how to build great REST APIs that scale in any language 👉 [https://treblle.com/ebooks/the-10-rest-commandments](https://treblle.com/ebooks/the-10-rest-commandments) 


## Support

If you have problems of any kind feel free to reach out via <https://treblle.com> or email vedran@treblle.com, and we'll
do our best to help you out.

## License

Copyright 2022, Treblle Limited. Licensed under the MIT license:
http://www.opensource.org/licenses/mit-license.php
