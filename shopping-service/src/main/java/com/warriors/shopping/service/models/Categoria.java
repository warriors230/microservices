package com.warriors.shopping.service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Categoria {
    private Long idCategoria;
    private String nombre;
}
