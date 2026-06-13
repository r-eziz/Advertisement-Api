# Advertisement API

## Project Purpose

This project provides a RESTful API to manage advertisement data and associated images. Users can upload advertisements, add images to advertisements, and download advertisements and images.

---

## Technologies

| Component | Technology |
|-----------|------------|
| Framework | Spring Boot 4.0.6 |
| Language | Java 21 |
| Database | PostgreSQL |
| ORM | Spring Data JPA (Hibernate) |
| Security | Spring Security (Basic Auth) |
| Documentation | SpringDoc OpenAPI (Swagger) |
| Utilities | Lombok |

---

## Project Structure

advertisement/
├── src/main/java/com/eziz/
│ ├── config/ # Configuration classes
│ ├── controller/ # Controller interfaces
│ │ └── impl/ # Controller implementations
│ ├── dto/ # Data Transfer Objects
│ ├── model/ # JPA Entity classes
│ ├── repository/ # JPA Repositories
│ ├── service/ # Service interfaces
│ │ └── impl/ # Service implementations
│ └── starter/ # Main application starter
├── src/main/resources/
│ └── application.properties # Application configuration
└── pom.xml # Maven dependencies
text


---

## API Endpoints

### Image Operations (`/rest/images`)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/upload` | Upload an image (multipart/form-data) | Required |
| `GET` | `/download/{id}` | Download image by ID | Not required |

### Advertisement Operations (`/rest/advertisement`)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/upload` | Create a new advertisement | Required |
| `GET` | `/download/{id}` | Get advertisement by ID | Not required |
| `GET` | `/download/all?pageSize=10&pageNumber=0` | Get all advertisements with pagination | Not required |

---

## Data Models

### Advertisement (Response DTO)

```json
{
  "id": 1,
  "name": "Advertisement name",
  "price": 99.99,
  "description": "Advertisement description"
}

DtoAdvertisementIU (Create/Update DTO)
json

{
  "id": 1,
  "name": "Advertisement name",
  "price": 99.99,
  "description": "Advertisement description",
  "idImages": [1, 2, 3]
}

Image DTO
json

{
  "id": 1
}

Database Schema
sql

-- advertisement table
CREATE TABLE advertisement (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL,
    description TEXT
);

-- image table
CREATE TABLE image (
    id SERIAL PRIMARY KEY,
    path VARCHAR(512)
);

-- advertisement_image (join table)
CREATE TABLE advertisement_image (
    advertisement_id INT,
    image_id INT,
    CONSTRAINT UC_adver_image UNIQUE (advertisement_id, image_id),
    FOREIGN KEY (advertisement_id) REFERENCES advertisement(id),
    FOREIGN KEY (image_id) REFERENCES image(id)
);

Installation and Running
Prerequisites

    Java 21

    Maven

    PostgreSQL (local)

Steps

1. Create PostgreSQL database:
sql

CREATE DATABASE "AdvertisementApi";

2. Configure application.properties:
properties

spring.datasource.url=jdbc:postgresql://localhost:5432/AdvertisementApi
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

file.upload.dir=/path/to/upload/directory

3. Compile the project:
bash

mvn clean compile

4. Run the application:
bash

mvn spring-boot:run

5. Application will run at http://localhost:8080
Security

Basic Authentication is used. Configuration:
properties

spring.security.user.name=admin
spring.security.user.password=admin123

Protected endpoints:

    POST /rest/images/upload

    POST /rest/advertisement/upload

Public endpoints:

    GET /rest/images/download/{id}

    GET /rest/advertisement/download/{id}

    GET /rest/advertisement/download/all

API Usage Examples
1. Upload an Image
bash

curl -X POST http://localhost:8080/rest/images/upload \
  -u admin:admin123 \
  -F "file=@/path/to/image.png"

Response:
json

{
  "id": 1
}

2. Create an Advertisement
bash

curl -X POST http://localhost:8080/rest/advertisement/upload \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "New Advertisement",
    "price": 49.99,
    "description": "Description of this advertisement",
    "idImages": [1]
  }'

Response:
json

{
  "id": 1,
  "name": "New Advertisement",
  "price": 49.99,
  "description": "Description of this advertisement"
}

3. Download an Advertisement
bash

curl -X GET http://localhost:8080/rest/advertisement/download/1

Response:
json

{
  "id": 1,
  "name": "New Advertisement",
  "price": 49.99,
  "description": "Description of this advertisement",
  "idImages": [1]
}

4. Get All Advertisements with Pagination
bash

curl -X GET "http://localhost:8080/rest/advertisement/download/all?pageSize=10&pageNumber=0"

5. Download an Image
bash

curl -X GET http://localhost:8080/rest/images/download/1 \
  --output image.png

Swagger Documentation

Once the application is running, Swagger UI is available at:
text

http://localhost:8080/swagger-ui/index.html

OpenAPI specification in JSON format:
text

http://localhost:8080/v3/api-docs

Known Issues and Notes

    Bug in advertisement download method: In dowloadAllAdvertisementToPage method, the imagesIds list is not cleared for each advertisement, causing image IDs from previous advertisements to be added to subsequent ones.

    Image path format: Images are stored with absolute paths, which may cause issues when the application runs on different environments. Using relative paths is recommended.

    Exception handling: No error handling mechanism is implemented. The get() method will throw an exception when Optional is empty.

    XSS security: No filtering for malicious HTML/JavaScript code in advertisement data.

Future Improvements

    Add Global Exception Handler (@ControllerAdvice)

    Add advertisement search and filtering endpoints

    Add image size and format validation

    Add image caching mechanism

    Add JWT authentication support

    Write unit and integration tests

    Add Docker containerization

    Add CDN support for images

Author

Developed by Eziz
License

This project was created for educational purposes.
