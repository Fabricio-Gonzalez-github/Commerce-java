package com.abmcommerce.abm.controllers;


import com.abmcommerce.abm.entities.Cart;
import com.abmcommerce.abm.entities.Client;
import com.abmcommerce.abm.entities.Invoice;
import com.abmcommerce.abm.sevices.ClientsServices;
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
@RequestMapping(path = "api/v1/clients")
@Tag(name = "Clients", description = "Endpoints for managing clients in the system. Allows creating, updating, listing, retrieving details, and deleting clients. Additionally, provides functionality to retrieve invoices and shopping carts associated with a specific client.")
public class ClientsControllers {


    @Autowired private ClientsServices service;

    @PostMapping()
    @Operation(summary = "Create a new client", description = "Adds a new client to the system and returns a confirmation message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> saveClient(@RequestBody Client client) {
        try {
            service.saveClient(client);
            return ResponseEntity.ok("Client Created successfully");
        }catch (Exception exception){
            throw  new RuntimeException("CREATE ERROR");
        }
    }


    @PutMapping()
    @Operation(summary = "Update an existing client", description = "Updates details of an existing client and returns a confirmation message. Returns 404 if the client is not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> updateClient(@RequestBody Client client) {
        try {
            Long id = client.getId();  /*Obtener el ID del cliente desde el cuerpo de la solicitud*/
            Optional<Client> optionalClient = service.readOneClient(id);
            if (optionalClient.isPresent()) {
                Client existingClient = optionalClient.get();

                /*Actualizar los campos del cliente existente*/
                existingClient.setName(client.getName());
                existingClient.setLastname(client.getLastname());
                existingClient.setDocNumber(client.getDocNumber());

                service.saveClient(existingClient);
                return ResponseEntity.ok("Client Updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found with id: " + id);
            }
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UPDATE ERROR: " + exception.getMessage());
        }
    }

    @GetMapping()
    @Operation(summary = "List all clients", description = "Retrieves a list of all clients in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of clients retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Client>  readAllClients() {
        try {
            return service.readClients();
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ALL CLIENTS ERROR");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific client", description = "Retrieves details of a specific client by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Optional <Client> readOneClient(@PathVariable("id")  Long id){
        try {
            return service.readOneClient(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE CLIENTS ERROR");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client", description = "Deletes a specific client by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> destroyOneClient(@PathVariable("id") Long id) {
        try {
            service.destroyOneClient(id);
            return ResponseEntity.ok("Client Delete successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE CLIENT ERROR");
        }
    }

    @GetMapping("/{id}/invoices")
    @Operation(summary = "Get invoices by client", description = "Retrieves a list of invoices associated with a specific client by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of invoices retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Invoice>> getInvoicesByClient(@PathVariable Long id) {
        List<Invoice> invoices = service.getInvoicesByClient(id);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}/carts")
    @Operation(summary = "Get carts by client", description = "Retrieves a list of shopping carts associated with a specific client by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of carts retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Cart>> getCartsByClient(@PathVariable Long id) {
        List<Cart> carts = service.getCartsByClient(id);
        return ResponseEntity.ok(carts);
    }





}
