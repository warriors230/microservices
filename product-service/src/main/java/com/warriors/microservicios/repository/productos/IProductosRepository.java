package com.warriors.microservicios.repository.productos;

import com.warriors.microservicios.entities.Categoria;
import com.warriors.microservicios.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductosRepository extends JpaRepository<Producto,Long> {
    public List<Producto>findByCategoria(Categoria categoria);
}
