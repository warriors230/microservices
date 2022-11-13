package com.warriors.shopping.service.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturaItems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long idItem;
    @Positive(message = "La cantidad debe de ser mayor que cero")
    private Double cantidad;
    private Double precio;

    @Column(name = "producto_id")
    private Long idProducto;
}
