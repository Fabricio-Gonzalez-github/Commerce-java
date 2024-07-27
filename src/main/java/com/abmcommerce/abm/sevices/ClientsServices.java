package com.abmcommerce.abm.sevices;


import com.abmcommerce.abm.entities.Cart;
import com.abmcommerce.abm.entities.Client;
import com.abmcommerce.abm.entities.Invoice;
import com.abmcommerce.abm.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsServices {

    @Autowired private ClientsRepository repository;

    public void saveClient(Client client){
        repository.save(client);
    }

    public List<Client> readClients() {
        return repository.findAll();
    }

    public Optional<Client> readOneClient(Long id) {
        return repository.findById(id);
    }

    public void destroyOneClient(Long id) {
        repository.deleteById(id);
    }

    public List<Invoice> getInvoicesByClient(Long clientId) {
        Optional<Client> clientOpt = readOneClient(clientId);
        if (clientOpt.isPresent()) {
            return clientOpt.get().getInvoices();
        } else {
            throw new RuntimeException("Client not found");
        }
    }

    public List<Cart> getCartsByClient(Long clientId) {
        Optional<Client> clientOpt = readOneClient(clientId);
        if (clientOpt.isPresent()) {
            return clientOpt.get().getCarts();
        } else {
            throw new RuntimeException("Client not found");
        }
    }

}


