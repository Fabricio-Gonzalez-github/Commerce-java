package com.abmcommerce.abm.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "clients")
@NoArgsConstructor  @EqualsAndHashCode @ToString
@Schema(description = "Represents a Client")
public class Client {

 @Schema(description = "Unique identifier of a client", example = "1")
 @Id @GeneratedValue(strategy = GenerationType.AUTO)
 @Getter @Setter private Long id;

 @Schema(description = "Name of client", example = "Pedro")
 @Getter @Setter private String name;

 @Schema(description = "Lastname of client", example = "Sanchez")
 @Getter @Setter private String lastname;

 @Schema(description = "Doc number of client", example = "45803527")
 @Getter @Setter private Integer docNumber;


 @OneToMany(mappedBy = "client", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
 @JsonIgnore @Getter @Setter private List<Invoice> invoices = new ArrayList<>();

 @OneToMany(mappedBy = "client", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
 @JsonIgnore @JsonManagedReference @Getter @Setter private List<Cart> carts = new ArrayList<>();


}
