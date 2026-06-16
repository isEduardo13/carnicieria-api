package com.isidro.carniceria_api.dto.Productos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductoResponse(
        Long id,
        String nombre,
        BigDecimal precioActual,
        Boolean esActivo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

        ) {
}
