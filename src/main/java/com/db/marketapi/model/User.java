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
@Table(name = "user")
public class User {
  @Id
  @Column(name = "user_id", nullable = false)
  @GeneratedValue
  private Long id;

  @NotNull private String username;

  private Integer numOfOrders;

  @OneToOne
  private Cart cart;

  @OneToOne
  private WishList wishlist;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  List<Order> orderHistory;
}
