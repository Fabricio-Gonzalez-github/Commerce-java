package com.abmcommerce.abm.controllers;


import com.abmcommerce.abm.entities.Client;
import com.abmcommerce.abm.sevices.ClientsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/clients")
public class ClientsControllers {


    @Autowired private ClientsServices service;

    @PostMapping()
    public ResponseEntity<String> saveClient(@RequestBody Client client) {
        try {
            service.saveClient(client);
            return ResponseEntity.ok("Client Created successfully");
        }catch (Exception exception){
            throw  new RuntimeException("CREATE ERROR");
        }
    }


    @PutMapping()
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
    public List<Client>  readAllClients() {
        try {
            return service.readClients();
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ALL ERROR");
        }
    }

    @GetMapping("/{id}")
    public Optional <Client> readOneClient(@PathVariable("id")  Long id){
        try {
            return service.readOneClient(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyOneClient(@PathVariable("id") Long id) {
        try {
            service.destroyOneClient(id);
            return ResponseEntity.ok("Client Delete successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE ERROR");
        }
    }





}
