package com.abmcommerce.abm.controllers;


import com.abmcommerce.abm.entities.Invoice;
import com.abmcommerce.abm.entities.InvoiceDetail;
import com.abmcommerce.abm.sevices.InvoicesDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/invoicesDetails")
public class InvoicesDetailsControllers {

    @Autowired private InvoicesDetailsServices invoicesDetailsServices;


    @PostMapping()
    public ResponseEntity<String> saveInvoiceDetails(@RequestBody InvoiceDetail invoiceDetail) {
        try {
            invoicesDetailsServices.saveInvoiceDetails(invoiceDetail);
            return ResponseEntity.ok("Invoice Created successfully");
        }catch (Exception exception){
            throw  new RuntimeException("CREATE ERROR");
        }
    }


    @GetMapping()
    public List<InvoiceDetail> readAllInvoiceDetails() {
        try {
            return invoicesDetailsServices.readInvoiceDetails();
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ALL ERROR");
        }
    }

    @GetMapping("/{id}")
    public Optional<InvoiceDetail> readOneInvoiceDetails(@PathVariable("id")  Long id){
        try {
            return invoicesDetailsServices.readOneInvoiceDetails(id);
        }catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("READ ONE ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyOneInvoiceDetails(@PathVariable("id") Long id) {
        try {
            invoicesDetailsServices.destroyInvoicesDetails(id);
            return ResponseEntity.ok("Invoice Delete Successfully");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE ERROR");
        }
    }
}
