package com.abmcommerce.abm.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "COMMERCE API",
                version = "1.0",
                description = "This API allows managing clients, products, shopping carts, and invoices in a commerce system"
        )
)
public class OpenApi {
}
