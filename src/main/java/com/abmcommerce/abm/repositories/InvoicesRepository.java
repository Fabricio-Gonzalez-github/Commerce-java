package com.abmcommerce.abm.repositories;


import com.abmcommerce.abm.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice,Long> {
}
