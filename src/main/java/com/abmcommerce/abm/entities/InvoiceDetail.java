package com.abmcommerce.abm.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "invoicesDetails")
@NoArgsConstructor @EqualsAndHashCode @ToString
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private  double price;

    @Getter @Setter private Integer amount;

    @ManyToOne @JoinColumn(name = "invoice_id")
    @JsonBackReference @Getter @Setter private Invoice invoice;




}
