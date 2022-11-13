package com.warriors.microservicios.services.impl;

import com.warriors.microservicios.entities.Categoria;
import com.warriors.microservicios.entities.Producto;
import com.warriors.microservicios.repository.productos.IProductosRepository;
import com.warriors.microservicios.services.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServicesImpl implements IProductoService {


    private final IProductosRepository productosRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodosLosProductos() {
        return productosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto traerProducto(Long id) {
        return productosRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        producto.setEstado("CREATED");
        producto.setNombre(producto.getNombre().toUpperCase());
        producto.setDescripcion(producto.getDescripcion().toUpperCase());
        return productosRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Producto producto) {
        Producto productoBD = traerProducto(producto.getIdProducto());
        if (productoBD == null) {
            return null;
        }
        productoBD.setNombre(producto.getNombre().toUpperCase());
        productoBD.setDescripcion(producto.getDescripcion().toUpperCase());
        productoBD.setCategoria(producto.getCategoria());
        productoBD.setPrecio(producto.getPrecio());
        return productosRepository.save(productoBD);
    }

    @Override
    @Transactional
    public Producto eliminarProducto(Long id) {
        Producto productoBD = traerProducto(id);
        if (productoBD == null) {
            return null;
        }
        productoBD.setEstado("DELETED");
        return productosRepository.save(productoBD);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorCategoria(Categoria categoria) {
        return productosRepository.findByCategoria(categoria);
    }

    @Override
    @Transactional
    public Producto actualizarStock(Long id, Double quantity) {
        Producto productoBD = traerProducto(id);
        if (productoBD == null) {
            return null;
        }
        if (quantity == null || quantity == 0D){
            quantity = 0D;
        }
        Double stock = productoBD.getStock() + quantity;
        productoBD.setStock(stock);
        return productosRepository.save(productoBD);
    }
}
