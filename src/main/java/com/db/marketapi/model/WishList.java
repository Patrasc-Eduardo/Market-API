package com.db.marketapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class WishList {
  @Id
  @Column(name = "wish_list_id", nullable = false)
  @GeneratedValue
  private Long wishListId;

//  @OneToOne(mappedBy = "wishlist")
//  User user;

  @OneToMany(cascade = CascadeType.ALL)
  List<Product> desiredProducts;
}
