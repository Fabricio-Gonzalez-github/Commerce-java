package com.abmcommerce.abm.repositories;


import com.abmcommerce.abm.entities.CartsItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CartsItemsRepository extends JpaRepository<CartsItems, Long> {
}
