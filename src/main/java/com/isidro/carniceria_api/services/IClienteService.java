package com.isidro.carniceria_api.services;

import com.isidro.carniceria_api.dto.clientes.ClienteRequest;
import com.isidro.carniceria_api.dto.clientes.ClienteResponse;


import java.util.List;

public interface IClienteService {

    List<ClienteResponse> findAll();

    ClienteResponse obtenerClientePorId(Long id);

    ClienteResponse registrarCliente(ClienteRequest request);

    ClienteResponse actualizarCliente(ClienteRequest request, Long id);

    void eliminarCliente(Long id);


}
