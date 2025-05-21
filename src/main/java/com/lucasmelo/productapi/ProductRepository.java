package com.lucasmelo.productapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository layer for product data access.
 * Currently implements an in-memory storage with sample data.
 */
@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product("74001755", "Ball Gown", "Full Body Outfits", 3548, 7, 1));
        products.add(new Product("74002423", "Shawl", "Accessories", 758, 12, 1));
        products.add(new Product("74002424", "T-Shirt", "Clothes", 500, 10, 1));
        products.add(new Product("74002425", "Jeans", "Clothes", 1800, 15, 0));
        products.add(new Product("74002426", "Hat", "Accessories", 300, 5, 1));
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        return products;
    }
}
