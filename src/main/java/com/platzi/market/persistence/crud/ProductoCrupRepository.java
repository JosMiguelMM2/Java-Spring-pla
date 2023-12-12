package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrupRepository extends CrudRepository<Producto, Integer> {
  //@Query(value="SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
  List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
  List<Producto> findAll(Sort sort);

  Optional<List<Producto>> findByStockLessThanAndEstado(int stock, boolean estado);
}
