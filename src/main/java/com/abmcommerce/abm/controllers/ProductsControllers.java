package com.abmcommerce.abm.controllers;


import com.abmcommerce.abm.entities.Product;
import com.abmcommerce.abm.sevices.ProductsServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
@Tag(name = "Products", description = "Endpoints for managing products in the system. Allows creating, updating, listing, retrieving details, and deleting products. Each product has attributes such as name, price, and stock.")
public class ProductsControllers {


    @Autowired private ProductsServices service;



    @PostMapping()
    @Operation(summary = "Create a new product", description = "Adds a new product to the system and returns a confirmation message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        try {
            service.saveProduct(product);
            return ResponseEntity.ok("Product Created successfully");
        }catch (Exception exception){
            throw  new RuntimeException("CREATE PRODUCT ERROR");
        }
    }

    @PutMapping()
    @Operation(summary = "Update an existing product", description = "Updates details of an existing product and returns a confirmation message. Returns 404 if the product is not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        try {
            Long id = product.getId();  /*Obtener el ID del cliente desde el cuerpo de la solicitud*/
            Optional<Product> optionalClient = service.readOneProduct(id);
            if (optionalClient.isPresent()) {
                Product existingClient = optionalClient.get();

                /*Actualizar los campos del cliente existente*/
                existingClient.setName(product.getName());
                existingClient.setPrice(product.getPrice());
                existingClient.setStock(product.getStock());

                service.saveProduct(existingClient);
                return ResponseEntity.ok("Product Updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
            }
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UPDATE ERROR: " + exception.getMessage());
        }
    }

    @GetMapping()
    @Operation(summary = "List all products", description = "Retrieves a list of all products in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Product> readAllProduct() {
        try {
            return service.readProduct();
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ALL PRODUCT ERROR");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific product", description = "Retrieves details of a specific product by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Optional<Product> readOneProduct(@PathVariable("id")  Long id){
        try {
            return service.readOneProduct(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE PRODUCT ERROR");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a specific product by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> destroyOneProduct(@PathVariable("id") Long id) {
        try {
            service.destroyOneProduct(id);
            return ResponseEntity.ok("Product Delete successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE PRODUCT ERROR");
        }
    }
    



}
