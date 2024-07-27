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


}
