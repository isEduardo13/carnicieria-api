package com.isidro.carniceria_api.mappers;

import com.isidro.carniceria_api.dto.clientes.ClienteRequest;
import com.isidro.carniceria_api.dto.clientes.ClienteResponse;
import com.isidro.carniceria_api.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente requestAEntidad (ClienteRequest clienteRequest){
        if (clienteRequest == null) return null;

        return Cliente.builder()
                .nombre(clienteRequest.nombre())
                .telefono(clienteRequest.telefono())
                .direccion(clienteRequest.direccion())
                .notas(clienteRequest.notas())
                .build();
    }

    public ClienteResponse EntidadAResponse (Cliente cliente){
        if (cliente == null) return null;

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getNotas(),
                cliente.getCreatedAt(),
                cliente.getUpdatedAt()
        );
    }
}
