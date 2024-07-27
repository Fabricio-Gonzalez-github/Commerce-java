package com.abmcommerce.abm.controllers;

import com.abmcommerce.abm.entities.Cart;
import com.abmcommerce.abm.entities.Client;
import com.abmcommerce.abm.sevices.CartsServices;
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
@RequestMapping(path = "api/v1/carts")
@Tag(name = "Carts", description = "Endpoints for managing shopping carts. Allows creating, listing, retrieving details, and deleting shopping carts in the system. Each cart is associated with a customer and can contain multiple products.")
public class CartsController {


    @Autowired CartsServices cartsServices;


    @PostMapping
    @Operation(summary = "Create a new shopping cart", description = "Creates a new shopping cart and returns a confirmation message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cart created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> saveCart(@RequestBody Cart cart) {
        try {
            cartsServices.saveCart(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cart saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save cart: " + e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "List all shopping carts", description = "Retrieves a list of all shopping carts. Returns 204 if no carts are found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of carts retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No carts found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Cart>> getAllCarts() {
        try {
            List<Cart> carts = cartsServices.readAllCarts();
            if (carts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(carts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a specific shopping cart", description = "Retrieves details of a specific shopping cart by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Cart not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Optional<Cart> readOneCart(@PathVariable("id")  Long id){
        try {
            return cartsServices.readOneCart(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE CART ONE ERROR");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a shopping cart", description = "Deletes a specific shopping cart by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> removeCart(@PathVariable("id") Long id) {
        try {
            cartsServices.removeCart(id);
            return ResponseEntity.ok("Cart Delete successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE CART ERROR");
        }
    }
}

