package com.productapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @NotBlank(message = "Barcode cannot be empty")
    @Pattern(regexp = "^[0-9]{8}$", message = "Barcode must be 8 digits")
    private String barcode;

    @NotBlank(message = "Item name cannot be empty")
    private String item;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private Integer price;

    @NotNull(message = "Discount cannot be null")
    @Positive(message = "Discount must be greater than or equal to 0")
    private Integer discount;

    @NotNull(message = "Availability cannot be null")
    @Pattern(regexp = "^[01]$", message = "Availability must be either 0 or 1")
    private Integer available;
}
