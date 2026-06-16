package com.isidro.carniceria_api.services;

import com.isidro.carniceria_api.dto.Productos.ProductoRequest;
import com.isidro.carniceria_api.dto.Productos.ProductoResponse;

import java.util.List;

public interface IProductoService {

    List<ProductoResponse> findAll();

    ProductoResponse obtenerPorId(Long id);

    ProductoResponse registrar(ProductoRequest productoRequest);

    ProductoResponse actualizar(ProductoRequest productoRequest, Long id);

    void eliminar(Long id);

}
