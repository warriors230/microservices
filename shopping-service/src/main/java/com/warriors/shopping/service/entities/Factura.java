package com.warriors.shopping.service.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_factura")
public class Factura implements Serializable {

    public Factura(){items = new ArrayList<>();}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;
    @Column(name = "numero_factura")
    private String numeroFactura;
    private String descripcion;
    @Column(name = "customer_id")
    private Integer customerID;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<FacturaItems>items;
    private String estado;

    @PrePersist
    public void prePersist(){
        this.fechaCreacion = new Date();
    }
}
