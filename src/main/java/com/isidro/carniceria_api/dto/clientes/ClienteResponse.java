package com.isidro.carniceria_api.dto.clientes;

import java.time.LocalDateTime;

public record ClienteResponse (
        Long id,
        String nombre,

        String telefono,

        String direccion,

        String notas,

        LocalDateTime createdAt,

        LocalDateTime updatedAt




){
}
