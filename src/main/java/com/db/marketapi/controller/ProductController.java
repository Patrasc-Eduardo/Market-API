package com.db.marketapi.controller;

import com.db.marketapi.model.Product;
import com.db.marketapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
  private final ProductService productService;

  @PostMapping("create")
  public Product createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
  }

  @PostMapping("add-to-cart/{userId}")
  public Product addProductToUserCart(
      @PathVariable("userId") Long userId, @RequestBody Product product) {
//    Optional<Product> getProduct = productService.getProductById(productId);
//    if (getProduct.isPresent()) {
      return productService.addProductToUserCart(userId, product);
//    }
//    return null;
  }

  @PostMapping("add-to-wish/{userId}/{productId}")
  public Product addProductToWishList(
      @PathVariable("userId") Long userId, @PathVariable Long productId) {
    //    Optional<Product> getProduct = productService.getProductById(productId);
    //    if (getProduct.isPresent()) {
    return productService.addProductToWishList(userId, productId);
    //    }
    // return null
  }

  @PostMapping("update")
  public Product updateProduct(@RequestBody Product product) {
    return productService.updateProduct(product);
  }

  //  @DeleteMapping("delete/{id}")
  //  public void deleteProductById(@PathVariable Long id) {
  //    productService.deleteProductById(id);
  //  }

  @DeleteMapping("delete-from-cart/{userId}/{productId}")
  public void deleteProductFromUserCart(
      @PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
    productService.deleteProductFromUserCart(userId, productId);
  }

  @DeleteMapping("delete-from-wishlist/{userId}/{productId}")
  public void deleteProductFromWishList(
      @PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
    productService.deleteProductFromWishList(userId, productId);
  }

  @GetMapping("/{id}")
  public Optional<Product> getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @GetMapping("/all")
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }
}
