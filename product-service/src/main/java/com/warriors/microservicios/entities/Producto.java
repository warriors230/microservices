package com.warriors.microservicios.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto implements Serializable {
    @Id
    @Column(name = "producto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @NotEmpty(message = "El campo nombre es requerido")
    private String nombre;
    private String descripcion;
    @Positive(message = "El campo stock debe de ser mayor a 0")
    private Double stock;
    @NotNull(message = " El campo precio es requerido")
    private Double precio;
    private String estado;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechacreacion")
    private Date fechaCreacion;
    @Valid
    @NotNull(message = "La categoria no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categoria categoria;
    private static final Long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
    }

    public void addCategoria(Categoria categoria) {
        this.setCategoria(categoria);
    }

}
