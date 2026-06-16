package com.isidro.carniceria_api.repositories;

import com.isidro.carniceria_api.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
