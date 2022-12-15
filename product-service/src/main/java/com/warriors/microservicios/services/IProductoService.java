package com.warriors.microservicios.services;

import com.warriors.microservicios.entities.Categoria;
import com.warriors.microservicios.entities.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarTodosLosProductos() throws RuntimeException;

    public Producto traerProducto(Long id) throws RuntimeException;

    public Producto crearProducto(Producto producto) throws RuntimeException;

    public Producto actualizarProducto(Producto producto) throws RuntimeException;

    public Producto eliminarProducto(Long id) throws RuntimeException;

    public List<Producto> buscarPorCategoria(Categoria categoria) throws RuntimeException;

    public Producto actualizarStock(Long id, Double quantity) throws RuntimeException;


}
