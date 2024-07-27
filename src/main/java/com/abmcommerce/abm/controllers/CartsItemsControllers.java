package com.abmcommerce.abm.controllers;


import com.abmcommerce.abm.entities.CartsItems;
import com.abmcommerce.abm.sevices.CartsItemsServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/cartsitems")
@Tag(name = "CartItems", description = "Endpoints for managing items within shopping carts. Allows creating, listing, retrieving details, and deleting cart items. Each cart item is associated with a specific product and belongs to a shopping cart.")
public class CartsItemsControllers {

    @Autowired private CartsItemsServices cartsItemsServices;


    @PostMapping
    @Operation(summary = "Create a new cart item", description = "Adds a new item to a shopping cart and returns a confirmation message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart item saved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> saveCartsItems(@RequestBody CartsItems cartsItems) {
        try {
            cartsItemsServices.saveCartsItems(cartsItems);
            return ResponseEntity.ok("CartsItem saved successfully with calculated price.");
        } catch (Exception exception) {
            return ResponseEntity.status(500).body("Error saving CartsItem: " + exception.getMessage());
        }
    }


    @GetMapping()
    @Operation(summary = "List all cart items", description = "Retrieves a list of all items in shopping carts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of cart items retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<CartsItems> readAllCartsItems() {
        try {
            return cartsItemsServices.readCartsItems();
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ALL ERROR");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific cart item", description = "Retrieves details of a specific cart item by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart item details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Cart item not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Optional<CartsItems> readOneCartsItems(@PathVariable("id")  Long id){
        try {
            return cartsItemsServices.readOneCartsItems(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE ERROR");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cart item", description = "Deletes a specific cart item by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart item deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> destroyOneCartsItems(@PathVariable("id") Long id) {
        try {
            cartsItemsServices.destroyCartsItems(id);
            return ResponseEntity.ok("Cart item deleted Successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE ERROR");
        }
    }
}
