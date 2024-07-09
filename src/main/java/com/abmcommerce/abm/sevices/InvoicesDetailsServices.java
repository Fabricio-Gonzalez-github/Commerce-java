package com.abmcommerce.abm.sevices;

import com.abmcommerce.abm.entities.Invoice;
import com.abmcommerce.abm.entities.InvoiceDetail;
import com.abmcommerce.abm.repositories.InvoicesDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoicesDetailsServices {


    @Autowired private InvoicesDetailsRepository invoicesDetailsRepository;


    public void saveInvoiceDetails(InvoiceDetail invoiceDetail) {
        invoicesDetailsRepository.save(invoiceDetail);
    }

    public List<InvoiceDetail> readInvoiceDetails() {
        return invoicesDetailsRepository.findAll();
    }

    public Optional<InvoiceDetail> readOneInvoiceDetails(Long id) {
        return invoicesDetailsRepository.findById(id);
    }

    public void destroyInvoicesDetails(Long id) {
        invoicesDetailsRepository.deleteById(id);
    }


}
