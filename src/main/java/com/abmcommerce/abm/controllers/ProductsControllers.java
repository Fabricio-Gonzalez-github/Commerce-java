package com.abmcommerce.abm.controllers;


import com.abmcommerce.abm.entities.Product;
import com.abmcommerce.abm.sevices.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductsControllers {


    @Autowired private ProductsServices service;



    @PostMapping()
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        try {
            service.saveProduct(product);
            return ResponseEntity.ok("Product Created successfully");
        }catch (Exception exception){
            throw  new RuntimeException("CREATE PRODUCT ERROR");
        }
    }

    @PutMapping()
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
    public List<Product> readAllProduct() {
        try {
            return service.readProduct();
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ALL PRODUCT ERROR");
        }
    }

    @GetMapping("/{id}")
    public Optional<Product> readOneProduct(@PathVariable("id")  Long id){
        try {
            return service.readOneProduct(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE PRODUCT ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyOneProduct(@PathVariable("id") Long id) {
        try {
            service.destroyOneProduct(id);
            return ResponseEntity.ok("Product Delete successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE PRODUCT ERROR");
        }
    }


    @PostMapping("/{clientId}/add/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable Long clientId, @PathVariable Long productId) {
        try {
           service.addToCart(clientId, productId);
            return ResponseEntity.ok("Added successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("ADD TO CART ERROR" , exception);
        }
    }

    @DeleteMapping("/{clientId}/remove/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long clientId, @PathVariable Long productId) {
        try {
            service.removeFromCart(clientId, productId);
            return ResponseEntity.ok("Product removed from cart successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("REMOVE FROM CART ERROR: " + exception.getMessage());
        }
    }

    @GetMapping("/clients/{clientId}/cart")
    public List<Product> getClientCart(@PathVariable Long clientId) throws Exception {
        return service.getClientCart(clientId);
    }



}
