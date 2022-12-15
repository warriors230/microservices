package com.warriors.shopping.service.controller;

import com.warriors.shopping.service.entities.Factura;
import com.warriors.shopping.service.services.IFacturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/factura")
public class FacturaController {
    @Autowired
    IFacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> findAll() {
        List<Factura> facturas = facturaService.findAll();
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(facturas);
    }

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        return ResponseEntity.ok().body(facturaService.crearFactura(factura));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Factura> findById(@PathVariable(name = "id") Long id) {
        Factura facturaDB = facturaService.findById(id);
        if (facturaDB == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturaDB);
    }
}
