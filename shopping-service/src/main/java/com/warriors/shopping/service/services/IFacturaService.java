package com.warriors.shopping.service.services;

import com.warriors.shopping.service.entities.Factura;

import java.util.List;

public interface IFacturaService {
    public List<Factura>findAll() throws RuntimeException;

    public Factura findById(Long id) throws RuntimeException;

    public Factura findByNumeroFactura(String numeroFactura) throws RuntimeException;

    public Factura crearFactura(Factura factura) throws RuntimeException;

    public Factura actualizarFactura(Factura factura) throws RuntimeException;

    public Factura eliminarFactura(Long id) throws RuntimeException;

    public List<Factura>findByCustomerId(Long customerid) throws RuntimeException;


}
