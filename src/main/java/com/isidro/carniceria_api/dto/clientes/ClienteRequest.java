package com.isidro.carniceria_api.dto.clientes;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(

        @NotBlank(message = "El nombre es requerido")
        String nombre,

        String telefono,

        String direccion,

        String notas
) {
}
