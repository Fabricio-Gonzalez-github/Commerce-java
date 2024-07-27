package com.abmcommerce.abm.entities;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "carts")
@NoArgsConstructor @EqualsAndHashCode @ToString
@Schema(description = "Represents a Shopping Cart")
public class Cart {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Schema(description = "delivered changes its value to true when creating an invoice for that cart")
    @Getter @Setter private Boolean delivered ;



    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference @Getter @Setter private Client client;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonManagedReference @Getter @Setter private List<CartsItems> cartsItems = new ArrayList<>();




}
