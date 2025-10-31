Username: user
Password: pass123

🧩 Spring Boot JWT Authentication API

A full-stack web application backend built with Spring Boot, featuring secure JWT-based authentication, modular architecture (Controllers, DTOs, Models, and Services), and tested with JUnit and Mockito.
The project is deployed on AWS Elastic Beanstalk (backend) and AWS CloudFront (frontend).

🚀 Features

User Authentication

Register and login using JWT tokens.

Role-based authorization (e.g., USER, ADMIN).

RESTful APIs

Structured endpoints with Controller, Service, Repository layers.

DTO (Data Transfer Objects)

Used for clean separation between API and persistence layers.

Unit Testing

Implemented with JUnit 5 and Mockito.

Secure Deployment

Backend hosted on AWS Elastic Beanstalk.

Frontend distributed globally via AWS CloudFront.

CI/CD Ready

Supports Docker builds and environment-based configuration.

⚙️ Tech Stack
Layer	Technology
Backend	Spring Boot 3, Java 21
Security	Spring Security, JWT
Database	MySQL (AWS RDS)
Testing	JUnit 5, Mockito
Build Tool	Maven
Deployment	AWS Elastic Beanstalk
Frontend	React + TypeScript (CloudFront)
Container	Docker
🔑 API Authentication Flow

Register → User sends credentials to /api/auth/register

Login → Authenticated via /api/auth/login

Token Generation → Server returns JWT access token

Authorized Access → Requests include Authorization: Bearer <token>

🧪 Running Tests

Run all tests:

mvn test


Example of unit tests included:

AuthControllerTest

UserServiceTest

JwtServiceTest

🐳 Run Locally with Docker
# Build Docker image
docker build -t springboot-jwt-app .

# Run container
docker run -p 8080:8080 springboot-jwt-app


Then open:
👉 http://localhost:8080/api/auth/login

☁️ Deployment (AWS)
Backend (Elastic Beanstalk)

Package your app:

mvn clean package


Deploy .jar or Docker image to Elastic Beanstalk.

Configure environment variables:

SPRING_DATASOURCE_URL=jdbc:mysql://...
SPRING_DATASOURCE_USERNAME=...
SPRING_DATASOURCE_PASSWORD=...
JWT_SECRET=...

Frontend (CloudFront)

Build your React/TypeScript app:

npm run build


Upload build/ files to an S3 bucket.

Distribute via CloudFront and link it to your backend’s HTTPS API.

🔗 Environment URLs
Project	URL
Frontend https://d1ypkqd9b2hjim.cloudfront.net

Backend API	http://project-backend-env.eba-xjb928px.us-east-1.elasticbeanstalk.com
