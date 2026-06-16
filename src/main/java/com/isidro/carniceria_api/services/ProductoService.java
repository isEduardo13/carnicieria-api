package com.isidro.carniceria_api.services;

import com.isidro.carniceria_api.dto.Productos.ProductoRequest;
import com.isidro.carniceria_api.dto.Productos.ProductoResponse;
import com.isidro.carniceria_api.entities.Producto;
import com.isidro.carniceria_api.exceptions.RecursoNoEncontradoException;
import com.isidro.carniceria_api.mappers.ProductoMapper;
import com.isidro.carniceria_api.repositories.ClienteRepository;
import com.isidro.carniceria_api.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> findAll() {
        log.info("Listando todos los productos");
        return productoRepository.findAll().stream()
                .map(productoMapper::EntidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponse obtenerPorId(Long id) {
        log.info("Obtendendo el id de producto");
        return productoMapper.EntidadAResponse(obtenerClientePorIdOException(id));
    }

    @Override
    public ProductoResponse registrar(ProductoRequest productoRequest) {
        log.info("Registrando el producto");
        Producto producto = productoMapper.requestAEntidad(productoRequest);
        productoRepository.save(producto);
        return productoMapper.EntidadAResponse(producto);
    }

    @Override
    public ProductoResponse actualizar(ProductoRequest productoRequest, Long id) {
        Producto producto = obtenerClientePorIdOException(id);
        log.info("Actualizando el id de producto");
        producto.actualizarDatos(productoRequest.nombre(), productoRequest.precioActual(), productoRequest.esActivo());
        log.info("Producto {} actualizado " , producto.getNombre());
        return productoMapper.EntidadAResponse(producto);
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = obtenerClientePorIdOException(id);
        log.info("Eliminando el id de producto");

        productoRepository.delete(producto);

    }

    private Producto obtenerClientePorIdOException(Long id) {
        log.info("Obteniendo el cliente  {}", id);
        return productoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("El producto con id: " + id + " no existe"));
    }
}
