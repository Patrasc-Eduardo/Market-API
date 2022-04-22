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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  public Cart createCart(Cart cart) {
    if (cart.getProducts() != null) {
      cart.setCartTotalPrice(cart.getProducts().stream().mapToDouble(Product::getPrice).sum());
      cart.setCartTotalProductsNo(cart.getProducts().size());
    }
    return cartRepository.save(cart);
  }

  public Cart createCartForUser(Cart cart, Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      cart.setCartTotalPrice(cart.getProducts().stream().mapToDouble(Product::getPrice).sum());
      cart.setCartTotalProductsNo(cart.getProducts().size());
      Cart newCart = cartRepository.save(cart);
      user.get().setCart(newCart);
      userRepository.save(user.get());
      return newCart;
    }
    return null;
  }

  public void deleteCartByIdForUser(Long id, Long userId) {
    Optional<User> user = userRepository.findById(userId);
    user.get().setCart(null);
    cartRepository.deleteById(id);
  }

  /*
  Pentru fiecare produs din cart cream un order nou. Primin un JSON care va contine
  ordered date -> date-ul actual, iar restul campuriilor null deoarece vor fi completate
  in viitor.
   */
  public User processCartMakeOrders(Long userId, Order order) {

    Optional<User> user = userRepository.findById(userId);
    if (user.isPresent()) {
      if (user.get().getCart().getProducts().size() > 0) {
        for (Product product : user.get().getCart().getProducts()) {
          Order newOrder = orderRepository.save(order);
          order.setProductOrdered(product);
          user.get().getOrderHistory().add(order);
          user.get()
              .getOrderHistory()
              .sort(
                  new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                      return o1.getOrderDate().compareTo(o2.getOrderDate());
                    }
                  });
          if (user.get().getNumOfOrders() != null ) {
            user.get().setNumOfOrders(user.get().getNumOfOrders() + 1);
          }
        }
      }
      return userRepository.save(user.get());
    }
    return null;
  }

  public List<Cart> getAllCartsFromUsersSortedByProdTotal() {
    return cartRepository.findByOrderByCartTotalProductsNoDesc();
  }

  public Cart updateCart(Cart cart) {
    return cartRepository.save(cart);
  }

  public List<Cart> getAllCarts() {
    return cartRepository.findAll();
  }

  public Optional<Cart> getCartById(Long id) {
    return cartRepository.findById(id);
  }

  public List<Cart> getAllCartsByTotalProdNo() {
    return cartRepository.findByOrderByCartTotalProductsNoDesc();
  }
}
