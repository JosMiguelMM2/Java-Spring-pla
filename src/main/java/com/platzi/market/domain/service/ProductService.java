package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;


  //Todos los productos
  public List<Product> getAll() {
    return productRepository.getAll();
  }

  //Obtener un solo producto
  public Optional<Product> getProduct(int productId) {
    return productRepository.getProduct(productId);
  }

  //Obtener productos por Id categoria
  public Optional<List<Product>> getByCategory(int CategoryId) {
    return productRepository.getByCategory(CategoryId);
  }

  //guardar un producto
  public Product save(Product product) {
    return productRepository.save(product);
  }

  //Eliminar un producto
  public boolean delete(int productId) {
    return getProduct(productId).map(product -> {
      productRepository.delete(productId);
      return true;
    }).orElse(false);
  }
  public boolean update(int productId, Product uproduct) {
    return getProduct(productId).map(product -> {
      uproduct.setProductId(productId);
      save(uproduct);
      return true;
    }).orElse(false);
  }

}
