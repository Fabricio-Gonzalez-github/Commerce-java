package com.abmcommerce.abm.controllers;


import com.abmcommerce.abm.entities.Invoice;
import com.abmcommerce.abm.sevices.InvoicesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/invoices")
public class InvoicesControllers {

    @Autowired private InvoicesServices invoicesServices;

    @PostMapping()
    public ResponseEntity<String> saveInvoice(@RequestBody Invoice invoice) {
        try {
            invoicesServices.saveInvoice(invoice);
            return ResponseEntity.ok("Invoice Created successfully");
        }catch (Exception exception){
            throw  new RuntimeException("CREATE ERROR");
        }
    }

    @GetMapping()
    public List<Invoice> readAllInvoices() {
        try {
            return invoicesServices.readInvoices();
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ALL ERROR");
        }
    }

    @GetMapping("/{id}")
    public Optional<Invoice> readOneInvoices(@PathVariable("id")  Long id){
        try {
            return invoicesServices.readOneInvoices(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyOneInvoices(@PathVariable("id") Long id) {
        try {
            invoicesServices.destroyOneInvoices(id);
            return ResponseEntity.ok("Invoice Delete Successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE ERROR");
        }
    }
}
