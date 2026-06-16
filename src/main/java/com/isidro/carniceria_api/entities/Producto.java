package com.isidro.carniceria_api.entities;

import com.isidro.carniceria_api.utils.StringUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre", unique = true, length = 150, nullable = false)
    private String nombre;

    @Column(name = "precio_actual", nullable = false)
    private BigDecimal precioActual;

    @Column(name = "activo", nullable = false)
    private Boolean esActivo;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public void actualizarDatos(String nombre, BigDecimal precioActual, Boolean esActivo) {
        actualizarNombre(nombre);
        validarPrecioActual(precioActual);
        validarActivo(esActivo);


    }

    public void validarNombre(String nombre) {
        StringUtils.validateSize(nombre, 5, 150, "El nombre es obligatorio debe tener entre 5 e 150");
    }


    public void actualizarNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
    }

    public void validarPrecioActual(BigDecimal precioActual) {

        if (precioActual == null || precioActual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio actual debe ser mayor que 0");
        }
        this.precioActual = precioActual;

    }


    public void validarActivo(Boolean esActivo) {
        if (esActivo == null ) return;
        this.esActivo = esActivo;
    }
}
