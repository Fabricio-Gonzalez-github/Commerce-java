package com.abmcommerce.abm.sevices;


import com.abmcommerce.abm.entities.Client;
import com.abmcommerce.abm.entities.Product;
import com.abmcommerce.abm.repositories.ClientsRepository;
import com.abmcommerce.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServices {

    @Autowired private ProductsRepository productsRepository;

    @Autowired private ClientsRepository clientsRepository;

    public void saveProduct(Product product){
        productsRepository.save(product);
    }

    public List<Product> readProduct() {
        return productsRepository.findAll();
    }

    public Optional<Product> readOneProduct(Long id) {
        return productsRepository.findById(id);
    }

    public void destroyOneProduct(Long id) {
        productsRepository.deleteById(id);
    }

    @Transactional
    public void addToCart (Long clientId, Long productId) throws Exception {
       Optional<Product> product = productsRepository.findById(productId);
       if (!product.isPresent()) {
           throw new Exception("Product not found with id:" + productId);
       }
       Optional<Client> client = clientsRepository.findById(clientId);
        if (!client.isPresent()) {
            throw new Exception("Client not found with id:" + clientId);
        }

        Product product1 = product.get();
        Client  client1 = client.get();

        client1.getCart().add(product1);

        product1.setClient(client1);

        clientsRepository.save(client1);
        productsRepository.save(product1);

        System.out.println("Product added to cart successfully");
    }





    @Transactional
    public void removeFromCart(Long clientId, Long productId) throws Exception {
        Optional<Product> productOptional = productsRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new Exception("Product not found with id:" + productId);
        }

        Optional<Client> clientOptional = clientsRepository.findById(clientId);
        if (!clientOptional.isPresent()) {
            throw new Exception("Client not found with id:" + clientId);
        }

        Product product = productOptional.get();
        Client client = clientOptional.get();

        client.getCart().remove(product);

        product.setClient(null);

        clientsRepository.save(client);
        productsRepository.save(product);

        System.out.println("Product removed from cart successfully");
    }

    public List<Product> getClientCart(Long clientId) throws Exception {
        Optional<Client> clientOptional = clientsRepository.findById(clientId);
        if (!clientOptional.isPresent()) {
            throw new Exception("Client not found with id:" + clientId);
        }

        Client client = clientOptional.get();
        return client.getCart();
    }
}
