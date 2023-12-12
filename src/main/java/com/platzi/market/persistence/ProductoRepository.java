package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrupRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
  @Autowired
  private ProductoCrupRepository productoCrupRepository;
  @Autowired
  private ProductMapper mapper;


  @Override
  public List<Product> getAll() {
    List<Producto> productos = (List<Producto>) productoCrupRepository.findAll(Sort.by(Sort.Order.asc("idProducto")));
    return mapper.toProducts(productos);
  }

  @Override
  public Optional<List<Product>> getByCategory(int CategoryId) {
    List<Producto> productos = productoCrupRepository.findByIdCategoriaOrderByNombreAsc(CategoryId);
    return Optional.of(mapper.toProducts(productos));
  }

  @Override
  public Optional<List<Product>> getScaserProducts(int amount) {
    Optional<List<Producto>> productos = productoCrupRepository.findByStockLessThanAndEstado(amount, true);
    return productos.map(prods -> mapper.toProducts(prods));
  }

  @Override
  public Optional<Product> getProduct(int productId) {
    return productoCrupRepository.findById(productId).map(producto -> mapper.toProduct(producto));

  }

  @Override
  public Product save(Product product) {
    Producto producto = mapper.toProducto(product);
    return mapper.toProduct(productoCrupRepository.save(producto));
  }

  @Override
  public void delete(int productId) {
    productoCrupRepository.deleteById(productId);
  }
}
