package com.productapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Filter products by price range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved products within price range"),
        @ApiResponse(responseCode = "400", description = "Invalid price range parameters")
    })
    @GetMapping("/filter/price/{initial_range}/{final_range}")
    public ResponseEntity<?> filterByPrice(
            @Parameter(description = "Initial price range") @PathVariable("initial_range") Integer initialRange,
            @Parameter(description = "Final price range") @PathVariable("final_range") Integer finalRange
    ) {
        try {
            if (initialRange == null || finalRange == null) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Price range parameters cannot be null");
            }
            if (initialRange > finalRange) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Initial price range cannot be greater than final price range");
            }
            List<Product> filtered = service.filterByPrice(initialRange, finalRange);
            return ResponseEntity.ok(filtered);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        }
    }

    @Operation(summary = "Sort products by price in ascending order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved sorted product names"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/sort/price")
    public ResponseEntity<?> sortByPrice() {
        try {
            List<String> sortedNames = service.sortByPrice();
            return ResponseEntity.ok(sortedNames);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error sorting products: " + e.getMessage());
        }
    }
}
