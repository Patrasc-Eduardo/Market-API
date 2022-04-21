package com.db.marketapi.service;

import com.db.marketapi.model.Cart;
import com.db.marketapi.model.Product;
import com.db.marketapi.model.User;
import com.db.marketapi.repository.CartRepository;
import com.db.marketapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final UserRepository userRepository;

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

  public void deleteCartById(Long id) {
    cartRepository.deleteById(id);
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
