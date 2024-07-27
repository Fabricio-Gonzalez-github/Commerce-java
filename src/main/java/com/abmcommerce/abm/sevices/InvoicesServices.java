package com.abmcommerce.abm.sevices;

import com.abmcommerce.abm.entities.Cart;
import com.abmcommerce.abm.entities.Invoice;
import com.abmcommerce.abm.repositories.CartsItemsRepository;
import com.abmcommerce.abm.repositories.CartsRepository;
import com.abmcommerce.abm.repositories.InvoicesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoicesServices {


    @Autowired private InvoicesRepository invoicesRepository;


    public void saveInvoice(Invoice invoice){
        invoicesRepository.save(invoice);

    }

    public List<Invoice> readInvoices() {
        return invoicesRepository.findAll();
    }

    public Optional<Invoice> readOneInvoices(Long id) {
        return invoicesRepository.findById(id);
    }

    public void destroyOneInvoices(Long id) {
        invoicesRepository.deleteById(id);
    }

    public Optional<Invoice> lastClientInvoice(Long clientId) {
        try {
            return invoicesRepository.findTopByClientIdOrderByLocalDateDesc(clientId);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error retrieving the latest invoice for client ID: " + clientId, exception);
        }
    }



}
