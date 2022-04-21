package com.db.marketapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

  //, orphanRemoval = true
  @OneToOne
  //@OnDelete(action = OnDeleteAction.CASCADE)
  //@JoinColumn( name = "cart_id")
  private Cart cart;

  //@OneToOne(cascade = CascadeType.REMOVE)
  //@OnDelete(action = OnDeleteAction.CASCADE)
  //@JoinColumn( name = "wishlist_id")
  @OneToOne
  private WishList wishlist;

  @OneToMany(cascade = CascadeType.ALL)
  List<Order> orderHistory;
}
