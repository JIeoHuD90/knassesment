package com.knassesment.knassesment.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Nonnull
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String skuCode;
    private double unitPrice;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines;


}
