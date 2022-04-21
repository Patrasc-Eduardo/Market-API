package com.db.marketapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users_table")
public class User {
  @Id
  @Column(name = "user_id", nullable = false)
  @GeneratedValue
  private Long id;

  @NotNull private String username;

  private Integer numOfOrders;

  @MapsId
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn( name = "cart_id")
  private Cart cart;

  @MapsId
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn( name = "wishlist_id")
  private WishList wishlist;

  @OneToMany(cascade = CascadeType.ALL)
  List<Order> orderHistory;
}
