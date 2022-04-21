package com.db.marketapi.service;

import com.db.marketapi.model.Cart;
import com.db.marketapi.model.Order;
import com.db.marketapi.repository.CartRepository;
import com.db.marketapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }

  public void deleteOrderById(Long id) {
    orderRepository.deleteById(id);
  }

  public Order updateOrder(Order order) {
    return orderRepository.save(order);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Optional<Order> getOrderById(Long id) {
    return orderRepository.findById(id);
  }
}
