package com.warriors.microservicios;

import com.warriors.microservicios.entities.Categoria;
import com.warriors.microservicios.entities.Producto;
import com.warriors.microservicios.repository.productos.IProductosRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductoRepositoryMockTest {
    @Autowired
    IProductosRepository productosRepository;

    @Test
    public void whenFindByCategory_thenResturnListProductos() {
        Producto producto01 = Producto.builder()
                .nombre("Computador")
                .categoria(Categoria.builder().idCategoria(1L).build())
                .descripcion("")
                .stock(Double.parseDouble("10"))
                .precio(Double.parseDouble("1200000.00"))
                .estado("A")
                .fechaCreacion(new Date())
                .build();
        productosRepository.save(producto01);
        List<Producto>founds = productosRepository.findByCategoria(producto01.getCategoria());

        Assertions.assertThat(founds.size()).isEqualTo(2);
    }

}
