package com.isidro.carniceria_api.entities;

import ch.qos.logback.core.util.StringUtil;
import com.isidro.carniceria_api.utils.StringUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;


    public void validarNombre(String nombre) {
        StringUtils.validateSize(nombre, 5 , 150, "El nombre es obligatorio debe tener entre 5 e 150");
    }

    public void actualizarNombre(String nuevoNombre) {
        this.nombre = nuevoNombre.trim();
    }

    public void actualizarTelefono(String nuevoTelefono) {
        if (nuevoTelefono != null && nuevoTelefono.length() > 20) {
            throw new IllegalArgumentException("El teléfono no puede tener más de 20 caracteres");
        }
        this.telefono = (nuevoTelefono == null) ? null : nuevoTelefono.trim();
    }

    public void actualizarDireccion(String nuevaDireccion) {
        if (nuevaDireccion != null && nuevaDireccion.length() > 100) {
            throw new IllegalArgumentException("La dirección no puede tener más de 100 caracteres");
        }
        this.direccion = (nuevaDireccion == null) ? null : nuevaDireccion.trim();

    }

    public void actualizarNotas(String nuevasNotas) {
        if (nuevasNotas != null && nuevasNotas.length() > 100) {
            throw new IllegalArgumentException("Las notas no pueden tener más de 100 caracteres");
        }
        this.notas = (nuevasNotas == null) ? null : nuevasNotas.trim();

    }





}
