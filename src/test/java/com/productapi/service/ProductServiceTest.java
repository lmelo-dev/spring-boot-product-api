package com.productapi.service;

import com.productapi.model.Product;
import com.productapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    private ProductService service;

    private final List<Product> sampleProducts = Arrays.asList(
        new Product("74001755", "Ball Gown", "Full Body Outfits", 3548, 7, 1),
        new Product("74002423", "Shawl", "Accessories", 758, 12, 1),
        new Product("74002424", "T-Shirt", "Clothes", 500, 10, 1),
        new Product("74002425", "Jeans", "Clothes", 1800, 15, 0),
        new Product("74002426", "Hat", "Accessories", 300, 5, 1)
    );

    @BeforeEach
    void setUp() {
        service = new ProductService(repository);
        when(repository.getAllProducts()).thenReturn(sampleProducts);
    }

    @Test
    void filterByPrice_ShouldReturnProductsWithinRange() {
        // Given
        int initialRange = 500;
        int finalRange = 1000;

        // When
        List<Product> result = service.filterByPrice(initialRange, finalRange);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(p -> p.getPrice() >= initialRange && p.getPrice() <= finalRange));
        assertTrue(result.stream().anyMatch(p -> p.getItem().equals("Shawl")));
        assertTrue(result.stream().anyMatch(p -> p.getItem().equals("T-Shirt")));
    }

    @Test
    void filterByPrice_ShouldReturnEmptyList_WhenNoProductsInRange() {
        // Given
        int initialRange = 5000;
        int finalRange = 6000;

        // When
        List<Product> result = service.filterByPrice(initialRange, finalRange);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void sortByPrice_ShouldReturnSortedProductNames() {
        // When
        List<String> result = service.sortByPrice();

        // Then
        assertEquals(5, result.size());
        assertEquals("Hat", result.get(0));      // 300
        assertEquals("T-Shirt", result.get(1));  // 500
        assertEquals("Shawl", result.get(2));    // 758
        assertEquals("Jeans", result.get(3));    // 1800
        assertEquals("Ball Gown", result.get(4)); // 3548
    }
} 