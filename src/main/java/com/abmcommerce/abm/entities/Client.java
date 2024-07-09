package com.abmcommerce.abm.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "clients")
@NoArgsConstructor  @EqualsAndHashCode @ToString
public class Client {

 @Id @GeneratedValue(strategy = GenerationType.AUTO)
 @Getter @Setter private Long id;

 @Getter @Setter private String name;

 @Getter @Setter private String lastname;

 @Getter @Setter private Integer docNumber;



 @ManyToMany
 @JoinTable(
         name = "client_product",
         joinColumns = @JoinColumn(name = "client_id"),
         inverseJoinColumns = @JoinColumn(name = "product_id")
 )
 @JsonIgnore @Getter @Setter private List<Product> cart = new ArrayList<>();


 @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 @JsonIgnore @Getter @Setter private List<Invoice> invoices = new ArrayList<>();


}
