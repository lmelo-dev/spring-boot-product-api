package com.productapi.constant;

/**
 * Constants for error messages used throughout the application.
 */
public final class ErrorMessages {
    private ErrorMessages() {
        // Prevent instantiation
    }

    public static final String PRICE_RANGE_NULL = "Price range parameters cannot be null";
    public static final String INVALID_PRICE_RANGE_ORDER = "Initial price range cannot be greater than final price range";
    public static final String INVALID_INITIAL_PRICE_RANGE_NEGATIVE = "Initial price range cannot be negative";
    public static final String INVALID_FINAL_PRICE_RANGE_NEGATIVE = "Final price range cannot be negative";
    public static final String INVALID_PARAMETER_TYPE = "Invalid parameter type: %s";
} 