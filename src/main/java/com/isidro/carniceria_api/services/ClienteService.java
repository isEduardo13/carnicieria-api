package com.isidro.carniceria_api.services;

import com.isidro.carniceria_api.dto.clientes.ClienteRequest;
import com.isidro.carniceria_api.dto.clientes.ClienteResponse;
import com.isidro.carniceria_api.entities.Cliente;
import com.isidro.carniceria_api.exceptions.RecursoNoEncontradoException;
import com.isidro.carniceria_api.mappers.ClienteMapper;
import com.isidro.carniceria_api.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> findAll() {
        log.info("Listando todos los productos");
        return clienteRepository.findAll().stream()
                .map(clienteMapper :: EntidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse obtenerClientePorId(Long id) {
        return clienteMapper.EntidadAResponse(obtenerClientePorIdOException(id));
    }

    @Override
    public ClienteResponse registrarCliente(ClienteRequest request) {
        log.info("Registrando cliente");
        Cliente cliente = clienteMapper.requestAEntidad(request);
        clienteRepository.save(cliente);
        return clienteMapper.EntidadAResponse(cliente);
    }

    @Override
    public ClienteResponse actualizarCliente(ClienteRequest request, Long id) {
        Cliente cliente = obtenerClientePorIdOException(id);
        log.info("Actualizando cliente");

        cliente.actualizarDatos(request.nombre(),request.telefono(),request.direccion(),request.notas());
        log.info("Cliente {} actualizado", cliente.getNombre());
        return  clienteMapper.EntidadAResponse(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerClientePorIdOException(id);
        log.info("Eliminando cliente");

        clienteRepository.delete(cliente);

        log.info("Cliente {} eliminado", cliente.getNombre());
    }

    private Cliente obtenerClientePorIdOException(Long id ){
        log.info("Obteniendo el cliente  {}", id);
        return clienteRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("El cliente con id: " + id + " no existe"));
    }
}
