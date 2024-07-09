package com.abmcommerce.abm.repositories;


import com.abmcommerce.abm.entities.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface InvoicesDetailsRepository extends JpaRepository<InvoiceDetail, Long> {
}
