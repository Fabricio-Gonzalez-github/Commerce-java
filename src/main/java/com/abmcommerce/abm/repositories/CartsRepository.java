package com.abmcommerce.abm.repositories;

import com.abmcommerce.abm.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartsRepository  extends JpaRepository< Cart, Long> {

    List<Cart> findByClientId(Long clientId);
}

