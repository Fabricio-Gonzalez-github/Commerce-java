package com.abmcommerce.abm.controllers;


import com.abmcommerce.abm.entities.Cart;
import com.abmcommerce.abm.entities.Invoice;
import com.abmcommerce.abm.sevices.CartsServices;
import com.abmcommerce.abm.sevices.InvoicesServices;
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
@RequestMapping(path = "api/v1/invoices")
@Tag(name = "Invoices", description = "Endpoints for managing invoices in the system. Allows creating, listing, retrieving details, and deleting invoices. Additionally, provides functionality to retrieve the latest invoice for a specific client. Also handles updating the delivery status of associated carts when an invoice is created.")
public class InvoicesControllers {

    @Autowired private InvoicesServices invoicesServices;

    @Autowired CartsServices cartsServices;

    @PostMapping
    @Operation(summary = "Create a new invoice", description = "Creates a new invoice and updates the delivery status of the associated cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> saveInvoice(@RequestBody Invoice invoice) {
        try {
            invoicesServices.saveInvoice(invoice);

            Cart cart = invoice.getCart();
            if (cart != null) {
                cartsServices.updateDeliveredStatus(cart.getId(), true);
            } else {
                throw new RuntimeException("Cart not found");
            }
            return ResponseEntity.ok("Invoice created successfully");
        } catch (Exception exception) {
            throw new RuntimeException("CREATE INVOICE ERROR", exception);
        }
    }

    @GetMapping()
    @Operation(summary = "List all invoices", description = "Retrieves a list of all invoices in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of invoices retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Invoice> readAllInvoices() {
        try {
            return invoicesServices.readInvoices();
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ALL INVOICES ERROR");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific invoice", description = "Retrieves details of a specific invoice by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Invoice not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Optional<Invoice> readOneInvoices(@PathVariable("id")  Long id){
        try {
            return invoicesServices.readOneInvoices(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE INVOICE ERROR");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an invoice", description = "Deletes a specific invoice by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> destroyOneInvoices(@PathVariable("id") Long id) {
        try {
            invoicesServices.destroyOneInvoices(id);
            return ResponseEntity.ok("Invoice Delete Successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE INVOICE ERROR");
        }
    }


    @GetMapping("/{clientId}/latest-invoice")
    @Operation(summary = "Get the latest invoice for a client", description = "Retrieves the most recent invoice for a specific client by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Latest invoice retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Invoice not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getLatestInvoice(@PathVariable Long clientId) {
        try {
            Optional<Invoice> invoice = invoicesServices.lastClientInvoice(clientId);
            if (invoice.isPresent()) {
                return ResponseEntity.ok(invoice.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(500).body("Error retrieving the latest invoice: " + exception.getMessage());
        }
    }
}
