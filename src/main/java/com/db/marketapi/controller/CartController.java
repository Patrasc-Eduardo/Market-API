package com.db.marketapi.controller;

import com.db.marketapi.model.Cart;
import com.db.marketapi.model.Product;
import com.db.marketapi.model.User;
import com.db.marketapi.model.WishList;
import com.db.marketapi.service.CartService;
import com.db.marketapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartController {

  private final CartService cartService;
  private final UserService userService;

  @PostMapping("create")
  public Cart createCart(@RequestBody Cart cart) {
    return cartService.createCart(cart);
  }

  @PostMapping("create-cart/{userId}")
  public Cart createCartForUser(@RequestBody Cart cart, @PathVariable Long userId) {
    return cartService.createCartForUser(cart, userId);
  }

  @PostMapping("update")
  public Cart updateCart(@RequestBody Cart cart) {
    return cartService.updateCart(cart);
  }

  @DeleteMapping("delete/{id}")
  public void deleteCartById(@PathVariable Long id) {
    cartService.deleteCartById(id);
  }

  @GetMapping("/{id}")
  public Optional<Cart> getCartById(@PathVariable Long id) {
    return cartService.getCartById(id);
  }

  @GetMapping("/all")
  public List<Cart> getAllCarts(@PathVariable Long id) {
    return cartService.getAllCarts();
  }

  @GetMapping("/all-by-prodNo")
  public List<Cart> getAllCartsByTotalProdNo() {
    return cartService.getAllCartsByTotalProdNo();
  }
}
