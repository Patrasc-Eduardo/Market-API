package com.db.marketapi.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders_table")
@Data
public class Order implements Comparable<Order> {
  @Id
  @Column(name = "order_id", nullable = false)
  @GeneratedValue
  private Long orderId;

  private Date orderDate;
  private Date shippedDate;

  @ManyToOne
  User user;

  @ManyToOne
  Cart cart;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn( name = "product_id")
  private Product productOrdered;

  @Override
  public int compareTo(@NotNull Order o) {
    return this.getOrderDate().compareTo(o.getOrderDate());
  }
}
