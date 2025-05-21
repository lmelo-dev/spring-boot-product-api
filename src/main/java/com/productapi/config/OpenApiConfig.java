package com.productapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration for OpenAPI/Swagger documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product API")
                        .description("REST API for product management with filtering and sorting capabilities. " +
                                   "All endpoints are prefixed with /api/products to follow REST best practices.")
                        .version("1.0"))
                .servers(List.of(
                    new Server()
                        .url("/")
                        .description("Default server URL")
                ));
    }
} 