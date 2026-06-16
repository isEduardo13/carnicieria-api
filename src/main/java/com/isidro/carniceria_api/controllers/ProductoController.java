package com.isidro.carniceria_api.controllers;

import com.isidro.carniceria_api.dto.Productos.ProductoRequest;
import com.isidro.carniceria_api.dto.Productos.ProductoResponse;
import com.isidro.carniceria_api.services.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
@AllArgsConstructor
@Validated
@Slf4j
public class ProductoController {

    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerProductos(){
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProducto(@PathVariable @Positive(message = "El id debe ser positivo")Long  id){
        return  ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> registrar(@RequestBody @Valid ProductoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.registrar(request));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable @Positive(message = "El id debe de ser positivo") Long id,  @Valid  @RequestBody ProductoRequest request){
        log.info("Actualizando el producto");
        ProductoResponse producto = productoService.actualizar(request,id);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @Positive(message = "El id debe de ser positivo") Long id){
        productoService.eliminar(id);

        return  ResponseEntity.noContent().build();
    }
}
