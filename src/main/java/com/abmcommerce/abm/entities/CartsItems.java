package com.abmcommerce.abm.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cartsitems")
@NoArgsConstructor @EqualsAndHashCode @ToString
@Schema(description = "Represents items in a cart")
public class CartsItems {

    @Schema(description = "Unique identifier of a cart item",example = "1")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Schema(description = "Quantity of a chosen product", example = "10")
    @Getter @Setter private Integer amount;

    @Schema(description = "Product price according to quantity")
    @Getter @Setter private Double priceOfAmount;


    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference @Getter @Setter private Cart cart;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;






}
