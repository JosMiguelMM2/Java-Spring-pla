package com.platzi.market.persistence.mapper;


import com.platzi.market.domain.PurchaseItem;
import com.platzi.market.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
  @Mappings({
    @Mapping(source = "id.idProducto", target = "productId"),
    @Mapping(source = "cantidad", target = "quantity"),
    @Mapping(source = "estado", target = "status")
  })
  PurchaseItem toPurchaseItem(CompraProducto compraProducto);

  @InheritInverseConfiguration
  @Mappings({
    @Mapping(target = "id.idCompra", ignore = true),
    @Mapping(target = "compra", ignore = true),
    @Mapping(target = "producto", ignore = true)
  })
  CompraProducto toCompraProducto(PurchaseItem purchaseItem);
}
