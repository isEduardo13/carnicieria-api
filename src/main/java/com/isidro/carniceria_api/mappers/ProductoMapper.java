package com.isidro.carniceria_api.mappers;

import com.isidro.carniceria_api.dto.Productos.ProductoRequest;
import com.isidro.carniceria_api.dto.Productos.ProductoResponse;
import com.isidro.carniceria_api.entities.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto requestAEntidad(ProductoRequest request) {
        if (request == null) return null;

        return Producto.builder()
                .nombre(request.nombre())
                .precioActual(request.precioActual())
                .esActivo(true)
                .build();
    }

    public ProductoResponse EntidadAResponse(Producto producto) {
        if (producto == null) return null;

        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecioActual(),
                producto.getEsActivo(),
                producto.getCreatedAt(),
                producto.getUpdatedAt()
        );
    }

}
