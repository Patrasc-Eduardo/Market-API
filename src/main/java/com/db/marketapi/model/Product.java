package com.db.marketapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
  @Id
  @Column(name = "product_id")
  @GeneratedValue
  private Long productId;

  private Long productCode;
  private String productDescription;
  private Double price;

  @OneToOne(cascade = CascadeType.REMOVE)
  Order order;
}
