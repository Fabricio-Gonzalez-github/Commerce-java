package com.abmcommerce.abm.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@NoArgsConstructor @EqualsAndHashCode @ToString
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter LocalDate localDate = LocalDate.now();

    @Getter @Setter private double totalAmount;

    @ManyToOne @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Getter @Setter private List<InvoiceDetail> details = new ArrayList<>();

}
