# Product API

A REST API for managing and querying product information with filtering and sorting capabilities.

## Features

- Filter products by price range
- Sort products by price in ascending order
- Swagger/OpenAPI documentation
- Comprehensive input validation
- Global error handling
- Unit test coverage

## Technical Stack

- Java 17
- Spring Boot 3.2.5
- Lombok
- SpringDoc OpenAPI (Swagger)
- JUnit 5 & Mockito for testing

## API Endpoints

### Filter Products by Price Range
- **Endpoint**: `GET /api/products/filter/price/{initial_range}/{final_range}`
- **Description**: Returns products within the specified price range
- **Validation**:
  - Price ranges must be non-negative
  - Initial range must be less than or equal to final range
  - Both ranges are required
- **Response Codes**:
  - `200`: Success
  - `400`: Invalid price range parameters

### Sort Products by Price
- **Endpoint**: `GET /api/products/sort/price`
- **Description**: Returns product names sorted by price in ascending order
- **Response Code**: `200`: Success

## Running the Project

1. Ensure you have Java 17 and Maven installed
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
6. **Validation**: Bean validation annotations for input validation
7. **Error Handling**: Global exception handler for consistent error responses

## Testing

The project includes comprehensive test coverage:

### Unit Tests
- **Controller Tests**:
  - Price range filtering
  - Input validation
  - Error scenarios
- **Service Tests**:
  - Filtering logic
  - Sorting logic
- **Exception Handler Tests**:
  - Error response formatting
  - Exception type handling

Run tests using:
```bash
./mvnw test
``` 