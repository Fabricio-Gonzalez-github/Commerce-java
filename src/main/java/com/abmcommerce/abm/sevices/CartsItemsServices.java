package com.abmcommerce.abm.sevices;

import com.abmcommerce.abm.entities.CartsItems;
import com.abmcommerce.abm.entities.Product;
import com.abmcommerce.abm.repositories.CartsItemsRepository;
import com.abmcommerce.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsItemsServices {


    @Autowired private CartsItemsRepository cartsItemsRepository;

    @Autowired private ProductsRepository productsRepository;


    public void saveCartsItems(CartsItems cartsItems) {
        Long productId = cartsItems.getProduct().getId();
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        /*Calcula el precio total*/
        double totalPrice = product.getPrice() * cartsItems.getAmount();

        cartsItems.setPriceOfAmount(totalPrice);

        cartsItemsRepository.save(cartsItems);
    }

    public List<CartsItems> readCartsItems() {
        return cartsItemsRepository.findAll();
    }

    public Optional<CartsItems> readOneCartsItems(Long id) {
        return cartsItemsRepository.findById(id);
    }

    public void destroyCartsItems(Long id) {
        cartsItemsRepository.deleteById(id);
    }


}
