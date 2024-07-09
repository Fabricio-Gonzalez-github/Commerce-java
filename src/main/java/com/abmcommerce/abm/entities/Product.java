package com.abmcommerce.abm.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor @EqualsAndHashCode @ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;

    @Getter @Setter private Integer price;

    @Getter @Setter private Integer Stock;

    @ManyToOne @JoinTable(name = "cart_client")
    @JsonIgnore @Getter @Setter private Client client;

}
