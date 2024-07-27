package com.abmcommerce.abm.repositories;


import com.abmcommerce.abm.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice,Long> {

    Optional<Invoice> findTopByClientIdOrderByLocalDateDesc(Long clientId);
}
