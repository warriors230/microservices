package com.warriors.shopping.service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Producto {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double stock;
    private Double precio;
    private String estado;
    private Categoria categoria;

}
