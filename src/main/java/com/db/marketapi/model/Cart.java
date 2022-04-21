package com.db.marketapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cart {
  @Id
  @Column(name = "cart_id", nullable = false)
  private Long cartId;

  private Double cartTotalPrice;
  private Integer cartTotalProductsNo;

  @OneToOne
  @JoinColumn( name = "user_id")
  private User user;

  @OneToMany(cascade = CascadeType.ALL)
  List<Product> products;
}
