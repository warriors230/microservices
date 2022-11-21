package com.warriors.shopping.service.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "tb_itemsFactura")
public class FacturaItems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    @JsonProperty("id_item")
    private Long idItem;
    @Positive(message = "La cantidad debe de ser mayor que cero")
    private Double cantidad;
    private Double precio;
    @JsonProperty("producto_id")
    @Column(name = "producto_id")
    private Long idProducto;

    @Transient
    private Double subTotal;

    public Double getSubTotal(){
        if(this.precio>0 && this.cantidad>0){
            return this.precio * this.cantidad;
        }else {
            return  0D;
        }
    }
}
