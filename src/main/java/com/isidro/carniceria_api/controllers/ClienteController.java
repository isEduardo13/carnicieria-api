package com.isidro.carniceria_api.controllers;

import com.isidro.carniceria_api.dto.clientes.ClienteRequest;
import com.isidro.carniceria_api.dto.clientes.ClienteResponse;
import com.isidro.carniceria_api.services.ClienteService;
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
@RequestMapping("api/clientes")
@AllArgsConstructor
@Validated
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obtenerClientes(){
        return   ResponseEntity.ok(clienteService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> ObtenerPorId(@PathVariable @Positive(message = "El id debe ser positivo") Long id){
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> registrar(@Valid @RequestBody ClienteRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.registrarCliente(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(@PathVariable @Positive long id, @Valid @RequestBody ClienteRequest request){
        log.info("Actualizando el id de la Sucursal {}" , id);
        ClienteResponse ClienteActualizado = clienteService.actualizarCliente(request, id);
        return ResponseEntity.ok(ClienteActualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable @Positive(message = "El id debe de ser positivo") long id){
        log.info("eliminando : {}"+ id);
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
