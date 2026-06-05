package com.isidro.carniceria_api.entities;

import com.isidro.carniceria_api.exceptions.InvalidClienteException;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "clientes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "notas", length = 100)
    private String notas;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;



    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new InvalidClienteException("El nombre no puede estar vacío");
        }
        if (nombre.length() > 150) {
            throw new InvalidClienteException("El nombre no puede tener más de 150 caracteres");
        }
    }

    private void validarLongitudes() {
        if (telefono != null && telefono.length() > 20) {
            throw new InvalidClienteException("El teléfono no puede tener más de 20 caracteres");
        }
        if (direccion != null && direccion.length() > 100) {
            throw new InvalidClienteException("La dirección no puede tener más de 100 caracteres");
        }
        if (notas != null && notas.length() > 100) {
            throw new InvalidClienteException("Las notas no pueden tener más de 100 caracteres");
        }
    }


    public void actualizarNombre(String nuevoNombre) {
        validarNombre(nuevoNombre);
        this.nombre = nuevoNombre.trim();
        touchUpdatedAt();
    }

    public void actualizarTelefono(String nuevoTelefono) {
        if (nuevoTelefono != null && nuevoTelefono.length() > 20) {
            throw new InvalidClienteException("El teléfono no puede tener más de 20 caracteres");
        }
        this.telefono = (nuevoTelefono == null) ? null : nuevoTelefono.trim();
        touchUpdatedAt();
    }

    public void actualizarDireccion(String nuevaDireccion) {
        if (nuevaDireccion != null && nuevaDireccion.length() > 100) {
            throw new InvalidClienteException("La dirección no puede tener más de 100 caracteres");
        }
        this.direccion = (nuevaDireccion == null) ? null : nuevaDireccion.trim();
        touchUpdatedAt();
    }

    public void actualizarNotas(String nuevasNotas) {
        if (nuevasNotas != null && nuevasNotas.length() > 100) {
            throw new InvalidClienteException("Las notas no pueden tener más de 100 caracteres");
        }
        this.notas = (nuevasNotas == null) ? null : nuevasNotas.trim();
        touchUpdatedAt();
    }

    private void touchUpdatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    @PrePersist
    private void prePersist() {
        Instant now = Instant.now();
        Timestamp ts = Timestamp.from(now);
        if (this.createdAt == null) this.createdAt = ts;
        this.updatedAt = ts;
        validarNombre(this.nombre);
        validarLongitudes();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = Timestamp.from(Instant.now());

        validarNombre(this.nombre);
        validarLongitudes();
    }

}
