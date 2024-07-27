package com.abmcommerce.abm.entities;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@NoArgsConstructor @EqualsAndHashCode @ToString
@Schema(description = "Represents a clients invoice")
public class Invoice {

    @Schema(description = "Unique identifier of a invoice", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Schema(description = "Date of purchase")
    @Getter @Setter LocalDate localDate = LocalDate.now();

    @Schema(description = "Total purchase price", example = "50000")
    @Getter @Setter private Double totalprice;


    @ManyToOne @JoinColumn(name = "client_id",nullable = false)
    @Getter @Setter private Client client;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @Getter @Setter private Cart cart;




}
