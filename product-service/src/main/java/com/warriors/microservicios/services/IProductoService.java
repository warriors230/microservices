package com.warriors.microservicios.services;

import com.warriors.microservicios.entities.Categoria;
import com.warriors.microservicios.entities.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarTodosLosProductos();

    public Producto traerProducto(Long id);

    public Producto crearProducto(Producto producto);

    public Producto actualizarProducto(Producto producto);

    public Producto eliminarProducto(Long id);

    public List<Producto> buscarPorCategoria(Categoria categoria);

    public Producto actualizarStock(Long id, Double quantity);


}
