package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping("all")
  @Operation(summary = "Show all data", description = "All products are shown")
  @ApiResponse(responseCode = "200", description = "OK")
  public ResponseEntity<List<Product>> getAll() {
    return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{Id}")
  @Operation(summary = "Search product by ID", description = "Enter the ID of the product you want to see")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK"),
    @ApiResponse(responseCode = "404", description = "Products not found")
  })
  public ResponseEntity<Product> getProduct(@Parameter(description = "The id of the product", required = true, example = "7") @PathVariable("Id") int productId) {
    return productService.getProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/category/{Id}")
  @Operation(summary = "Search product by category ID", description = "Enter the ID of the category of the product you want to search for")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK"),
    @ApiResponse(responseCode = "404", description = "Products not found")
  })
  public ResponseEntity<List<Product>> getByCategory(@PathVariable("Id") int CategoryId) {
    return productService.getByCategory(CategoryId).map(products -> new ResponseEntity<>(products, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/save")
  @Operation(summary = "Save", description = "Enter the new product you want to save")
  public ResponseEntity<Product> save(@RequestBody Product product) {
    return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Delete a product", description = "Enter the ID of the product you want to delete")
  public ResponseEntity<Void> delete(@PathVariable("id") int productId) {
    if (productService.delete(productId)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/update/{id}")
  @Operation(summary = "Update a product", description = "Enter the ID of the product you want to update along with the new product data for that ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK"),
    @ApiResponse(responseCode = "404", description = "Products not found")
  })
  public ResponseEntity<String> update(@PathVariable("id") int productId, @RequestBody Product product) {
    if (productService.update(productId, product)) {
      return new ResponseEntity<>("Actualización exitosa", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_FOUND);
    }
  }
}
