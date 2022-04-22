package com.db.marketapi.service;

import com.db.marketapi.model.Cart;
import com.db.marketapi.model.Product;
import com.db.marketapi.model.User;
import com.db.marketapi.model.WishList;
import com.db.marketapi.repository.CartRepository;
import com.db.marketapi.repository.ProductRepository;
import com.db.marketapi.repository.UserRepository;
import com.db.marketapi.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final CartRepository cartRepository;
  private final WishlistRepository wishListRepository;

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public void deleteProductById(Long id) {
    productRepository.deleteById(id);
  }

  public Product addProductToUserCart(Long userId, Product product) {
    Optional<User> user = userRepository.findById(userId);

    assert user.get().getCart() != null;

    Cart userCart;
    if (user.isPresent()) {
      userCart = user.get().getCart();
      if (userCart != null) {
        userCart.setCartTotalProductsNo(userCart.getCartTotalProductsNo() + 1);
        userCart.setCartTotalPrice(userCart.getCartTotalPrice() + product.getPrice());
        user.ifPresent(value -> value.getCart().getProducts().add(product));
        System.err.println(user.get());
        //userRepository.save(user.get());
        cartRepository.save(userCart);
      }
//      user.ifPresent(value -> value.getCart().getProducts().add(product));
//      System.err.println(user.get());
      userRepository.save(user.get());
      return product;
    }
    return null;
  }

  public Product addProductToWishList(Long userId, Product product) {
    Optional<User> user = userRepository.findById(userId);

    assert user.get().getCart() != null;

    WishList userWishList;
    if (user.isPresent()) {
      userWishList = user.get().getWishlist();
      if (userWishList != null) {

        wishListRepository.save(userWishList);
      }
      user.ifPresent(value -> value.getWishlist().getDesiredProducts().add(product));
      userRepository.save(user.get());
      return product;
    }
    return null;
  }

  public void deleteProductFromUserCart(Long userId, Long productId) {
    Optional<User> user = userRepository.findById(userId);
    Cart userCart;
    if (user.isPresent()) {
      userCart = user.get().getCart();
      if (userCart != null) {
        userCart
            .getProducts()
            .removeIf(
                prod ->
                    Long.compare(prod.getProductId(), productId)
                        == 0); // sterg daca gasesc produsul cu id-ul cautat
        cartRepository.save(userCart);
      }
    }
  }

  public void deleteProductFromWishList(Long userId, Long productId) {
    Optional<User> user = userRepository.findById(userId);
    WishList userWishList;
    if (user.isPresent()) {
      userWishList = user.get().getWishlist();
      if (userWishList != null) {
        userWishList
            .getDesiredProducts()
            .removeIf(
                prod ->
                    Long.compare(prod.getProductId(), productId)
                        == 0); // sterg daca gasesc produsul cu id-ul cautat
        wishListRepository.save(userWishList);
      }
    }
  }

  public Product updateProduct(Product product) {
    return productRepository.save(product);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }
}
