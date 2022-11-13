package com.warriors.shopping.service.repository;

import com.warriors.shopping.service.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IFacturaRepository extends JpaRepository<Factura,Long> {
    public List<Factura>findByCustomerId(Long Customerid);
    public Factura findByNumeroFactura(String numeroFactura);
}
