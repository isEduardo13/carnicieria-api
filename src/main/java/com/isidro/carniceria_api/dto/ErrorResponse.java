package com.isidro.carniceria_api.dto;

public record ErrorResponse(
        int codigo,
        String mensaje
) {
}
