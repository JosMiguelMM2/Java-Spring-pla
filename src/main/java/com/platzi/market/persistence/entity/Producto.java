package com.platzi.market.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto")
  private Integer idProducto;

  private String nombre;

  @Column(name = "id_categoria")
  private Integer idCategoria;

  @Column(name = "codigo_barras")
  private String codigoBarras;

  @Column(name = "precio_venta")
  private Double precio;

  @Column(name = "cantidad_stock")
  private Integer stock;

  private Boolean estado;

  @ManyToOne
  @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
  private Categoria categoria;

  @OneToMany(mappedBy = "producto")
  private List<CompraProducto> compraProductos;

  public Integer getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(Integer idProducto) {
    this.idProducto = idProducto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(Integer idCategoria) {
    this.idCategoria = idCategoria;
  }

  public String getCodigoBarras() {
    return codigoBarras;
  }

  public void setCodigoBarras(String codigoBarras) {
    this.codigoBarras = codigoBarras;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Boolean getEstado() {
    return estado;
  }

  public void setEstado(Boolean estado) {
    this.estado = estado;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public List<CompraProducto> getCompraProductos() {
    return compraProductos;
  }

  public void setCompraProductos(List<CompraProducto> compraProductos) {
    this.compraProductos = compraProductos;
  }
}
