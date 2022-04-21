package com.db.marketapi.service;

import com.db.marketapi.model.Cart;
import com.db.marketapi.model.Order;
import com.db.marketapi.model.Product;
import com.db.marketapi.model.User;
import com.db.marketapi.repository.CartRepository;
import com.db.marketapi.repository.OrderRepository;
import com.db.marketapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;

  public User createUser(User user) {
    if (user.getOrderHistory() != null) {
      user.setNumOfOrders(user.getOrderHistory().size());
    }
    return userRepository.save(user);
  }

  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }

  public List<User> getAllUsersSortedByNumOfOrders() {
    return userRepository.findByOrderByNumOfOrdersAsc();
  }

  public User updateUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }
}
