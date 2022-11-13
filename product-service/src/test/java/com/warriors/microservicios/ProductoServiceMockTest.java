package com.warriors.microservicios;

import com.warriors.microservicios.entities.Categoria;
import com.warriors.microservicios.entities.Producto;
import com.warriors.microservicios.repository.productos.IProductosRepository;
import com.warriors.microservicios.services.IProductoService;
import com.warriors.microservicios.services.impl.ProductoServicesImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SuppressWarnings("deprecation")
@SpringBootTest
public class ProductoServiceMockTest {

    @Mock
    private IProductosRepository productosRepository;

    private IProductoService productoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        productoService = new ProductoServicesImpl(productosRepository);
        Producto plancha = Producto.builder()
                .nombre("plancha")
                .categoria(Categoria.builder().idCategoria(1L).build())
                .descripcion("Plancha Acer")
                .stock(Double.parseDouble("10"))
                .precio(Double.parseDouble("120000.00"))
                .estado("CREATED")
                .fechaCreacion(new Date())
                .build();

        Mockito.when(productosRepository.findById(1L)).thenReturn(Optional.of(plancha));
        Mockito.when(productosRepository.save(plancha)).thenReturn(plancha);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct() {
        Producto found = productoService.traerProducto(1L);
        Assertions.assertThat(found.getNombre()).isEqualTo("plancha");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock() {
        Producto newStock = productoService.actualizarStock(1L, 5D/*Double.parseDouble("5")*/);
        Assertions.assertThat(newStock.getStock()).isEqualTo(15);

    }

}
