package com.abmcommerce.abm.entities;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor @EqualsAndHashCode @ToString
@Schema(description = "Represents products to be sold")
public class Product {

    @Schema(description = "Unique identifier of a product", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Schema(description = "Name of product", example = "zapatillaz")
    @Getter @Setter private String name;

    @Schema(description = "Price of product", example = "90000")
    @Getter @Setter private Integer price;

    @Schema(description = "Stock of product", example = "20")
    @Getter @Setter private Integer Stock;


}
