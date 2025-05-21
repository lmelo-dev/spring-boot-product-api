package com.productapi.controller;

import com.productapi.constant.ErrorMessages;
import com.productapi.model.Product;
import com.productapi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService service;

    private ProductController controller;

    private final List<Product> sampleProducts = Arrays.asList(
        new Product("74001755", "Ball Gown", "Full Body Outfits", 3548, 7, 1),
        new Product("74002423", "Shawl", "Accessories", 758, 12, 1),
        new Product("74002424", "T-Shirt", "Clothes", 500, 10, 1),
        new Product("74002425", "Jeans", "Clothes", 1800, 15, 0),
        new Product("74002426", "Hat", "Accessories", 300, 5, 1)
    );

    @BeforeEach
    void setUp() {
        controller = new ProductController(service);
    }

    @Test
    void filterByPrice_ShouldReturnFilteredProducts() {
        // Given
        int initialRange = 500;
        int finalRange = 1000;
        List<Product> expectedProducts = Arrays.asList(
            new Product("74002423", "Shawl", "Accessories", 758, 12, 1),
            new Product("74002424", "T-Shirt", "Clothes", 500, 10, 1)
        );
        when(service.filterByPrice(initialRange, finalRange)).thenReturn(expectedProducts);

        // When
        ResponseEntity<?> response = controller.filterByPrice(initialRange, finalRange);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedProducts, response.getBody());
        verify(service).filterByPrice(initialRange, finalRange);
    }

    @Test
    void filterByPrice_ShouldThrowException_WhenInitialRangeIsGreaterThanFinalRange() {
        // Given
        int initialRange = 1000;
        int finalRange = 500;

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> controller.filterByPrice(initialRange, finalRange)
        );
        assertEquals(ErrorMessages.INVALID_PRICE_RANGE_ORDER, exception.getMessage());
        verify(service, never()).filterByPrice(anyInt(), anyInt());
    }

    @Test
    void filterByPrice_ShouldThrowException_WhenPriceRangeIsNegative() {
        // Test initial range negative
        IllegalArgumentException exception1 = assertThrows(
            IllegalArgumentException.class,
            () -> controller.filterByPrice(-100, 500)
        );
        assertEquals(ErrorMessages.INVALID_INITIAL_PRICE_RANGE_NEGATIVE, exception1.getMessage());
        verify(service, never()).filterByPrice(anyInt(), anyInt());

        // Test final range negative
        IllegalArgumentException exception2 = assertThrows(
            IllegalArgumentException.class,
            () -> controller.filterByPrice(100, -500)
        );
        assertEquals(ErrorMessages.INVALID_FINAL_PRICE_RANGE_NEGATIVE, exception2.getMessage());
        verify(service, never()).filterByPrice(anyInt(), anyInt());
    }

    @Test
    void filterByPrice_ShouldThrowException_WhenPriceIsNull() {
        // Given
        Integer initialRange = null;
        Integer finalRange = 500;

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> controller.filterByPrice(initialRange, finalRange)
        );
        assertEquals(ErrorMessages.PRICE_RANGE_NULL, exception.getMessage());
        verify(service, never()).filterByPrice(anyInt(), anyInt());
    }

    @Test
    void sortByPrice_ShouldReturnSortedProductNames() {
        // Given
        List<String> expectedNames = Arrays.asList("Hat", "T-Shirt", "Shawl", "Jeans", "Ball Gown");
        when(service.sortByPrice()).thenReturn(expectedNames);

        // When
        ResponseEntity<?> response = controller.sortByPrice();

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedNames, response.getBody());
        verify(service).sortByPrice();
    }
} 