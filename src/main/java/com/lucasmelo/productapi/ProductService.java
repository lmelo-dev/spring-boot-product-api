package com.lucasmelo.productapi;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for product-related operations.
 * Handles business logic for filtering and sorting products.
 */
@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Filters products by price range.
     *
     * @param initialRange The minimum price (inclusive)
     * @param finalRange The maximum price (inclusive)
     * @return List of products within the specified price range
     * @throws IllegalArgumentException if price ranges are negative
     */
    public List<Product> filterByPrice(int initialRange, int finalRange) {
        if (initialRange < 0 || finalRange < 0) {
            throw new IllegalArgumentException("Price ranges cannot be negative");
        }
        return repository.getAllProducts().stream()
                .filter(product -> product.getPrice() >= initialRange && product.getPrice() <= finalRange)
                .collect(Collectors.toList());
    }

    /**
     * Sorts products by price in ascending order and returns their names.
     *
     * @return List of product names sorted by price (lowest to highest)
     */
    public List<String> sortByPrice() {
        return repository.getAllProducts().stream()
                .sorted((p1, p2) -> Integer.compare(p1.getPrice(), p2.getPrice()))
                .map(Product::getItem)
                .collect(Collectors.toList());
    }
}
