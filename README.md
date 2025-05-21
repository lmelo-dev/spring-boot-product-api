# Product API

A REST API for managing and querying product information with filtering and sorting capabilities.

## Features

- Filter products by price range
- Sort products by price in ascending order
- Swagger/OpenAPI documentation
- Input validation
- Error handling

## Technical Stack

- Java 8+
- Spring Boot
- Lombok
- SpringDoc OpenAPI (Swagger)

## API Endpoints

### Filter Products by Price Range
- **Endpoint**: `GET /filter/price/{initial_range}/{final_range}`
- **Description**: Returns products within the specified price range
- **Response Codes**:
  - `200`: Success
  - `400`: Invalid price range parameters

### Sort Products by Price
- **Endpoint**: `GET /sort/price`
- **Description**: Returns product names sorted by price in ascending order
- **Response Code**: `200`: Success

## Running the Project

1. Ensure you have Java 8+ and Maven installed
2. Clone the repository
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Access the Swagger documentation at: `http://localhost:8080/swagger-ui.html`

## Technical Decisions

1. **Spring Boot**: Chosen for its robust ecosystem and ease of development
2. **Lombok**: Reduces boilerplate code and improves readability
3. **Stream API**: Used for efficient filtering and sorting operations
4. **Swagger/OpenAPI**: Added for better API documentation and testing
5. **In-memory Repository**: Used for simplicity, can be easily replaced with a database implementation

## Testing

The project includes test cases for:
- Price range filtering
- Price-based sorting
- Input validation
- Error handling

Run tests using:
```bash
./mvnw test
``` 