package com.example.homework27.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Product name should not be empty")
    @Column(columnDefinition = "varchar(25) not null ")
    private  String name;

    @NotNull(message = "Product price should not be empty ")
    @Column(columnDefinition = "int not null")
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Order> orderSet;
}
