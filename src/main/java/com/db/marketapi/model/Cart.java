package com.db.marketapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cart {
  @Id
  @Column(name = "cart_id", nullable = false)
  @GeneratedValue
  private Long cartId;

  private Double cartTotalPrice;
  private Integer cartTotalProductsNo;

   @OneToOne
   private User user;

  @OneToMany
  List<Product> products;
}
