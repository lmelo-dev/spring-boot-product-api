package com.productapi.controller;

import com.productapi.constant.ErrorMessages;
import com.productapi.model.Product;
import com.productapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for product-related operations.
 * Provides endpoints for filtering and sorting products.
 */
@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Filters products by price range.
     * Validates that:
     * - Price ranges are not null
     * - Price ranges are not negative
     * - Initial range is not greater than final range
     *
     * @param initialRange The minimum price (inclusive)
     * @param finalRange The maximum price (inclusive)
     * @return ResponseEntity containing the list of products within the price range
     * @throws IllegalArgumentException if price range validation fails
     */
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
        if (initialRange == null || finalRange == null) {
            throw new IllegalArgumentException(ErrorMessages.PRICE_RANGE_NULL);
        }
        if (initialRange < 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_INITIAL_PRICE_RANGE_NEGATIVE);
        }
        if (finalRange < 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_FINAL_PRICE_RANGE_NEGATIVE);
        }
        if (initialRange > finalRange) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PRICE_RANGE_ORDER);
        }
        List<Product> filtered = service.filterByPrice(initialRange, finalRange);
        return ResponseEntity.ok(filtered);
    }

    /**
     * Sorts products by price in ascending order and returns their names.
     *
     * @return ResponseEntity containing the list of product names sorted by price
     */
    @Operation(summary = "Sort products by price in ascending order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved sorted product names")
    })
    @GetMapping("/sort/price")
    public ResponseEntity<?> sortByPrice() {
        List<String> sortedNames = service.sortByPrice();
        return ResponseEntity.ok(sortedNames);
    }
}
