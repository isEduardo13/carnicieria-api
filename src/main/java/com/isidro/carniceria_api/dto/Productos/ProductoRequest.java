package com.isidro.carniceria_api.dto.Productos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductoRequest(
        @NotBlank(message = "El nombre es requerido")
        String nombre,

        @NotNull(message = "El precio del producto es requerido")
        @Positive(message = "El precio debe de ser positivo o mayor a 0")
        BigDecimal precioActual,

        Boolean esActivo


) {
}
