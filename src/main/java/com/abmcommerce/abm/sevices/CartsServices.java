package com.abmcommerce.abm.sevices;

import com.abmcommerce.abm.entities.Cart;
import com.abmcommerce.abm.entities.Client;
import com.abmcommerce.abm.repositories.CartsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsServices {


    @Autowired private CartsRepository cartsRepository;

    public void saveCart(Cart cart){
      cartsRepository.save(cart);

    }

    public List<Cart> readAllCarts() {
        return cartsRepository.findAll();
    }

    public Optional<Cart> readOneCart(Long id) {
        return cartsRepository.findById(id);
    }

    public void removeCart(Long id) {
        cartsRepository.deleteById(id);
    }

    @Transactional
    public void updateDeliveredStatus(Long cartId, boolean delivered) {
        Cart cart = cartsRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));
        cart.setDelivered(delivered);
        cartsRepository.save(cart);
    }


}
