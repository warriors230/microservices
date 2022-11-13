package com.warriors.shopping.service.services.impl;

import com.warriors.shopping.service.entities.Factura;
import com.warriors.shopping.service.repository.IFacturaRepository;
import com.warriors.shopping.service.repository.IItemFacturaRepository;
import com.warriors.shopping.service.services.IFacturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class FacturaService implements IFacturaService {

    @Autowired
    private IFacturaRepository facturaRepository;

    @Autowired
    IItemFacturaRepository itemFacturaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Factura findByNumeroFactura(String numeroFactura) {
        return facturaRepository.findByNumeroFactura(numeroFactura);
    }

    @Transactional
    @Override
    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Transactional
    @Override
    public Factura actualizarFactura(Factura factura) {
        Factura facturaBD = this.findById(factura.getIdFactura());
        if (facturaBD == null) {
            return null;
        }
        facturaBD.setCustomerId(factura.getCustomerId());
        facturaBD.setDescripcion(factura.getDescripcion());
        facturaBD.setNumeroFactura(factura.getNumeroFactura());
        facturaBD.getItems().clear();
        facturaBD.setItems(factura.getItems());
        return facturaRepository.save(facturaBD);
    }

    @Transactional
    @Override
    public Factura eliminarFactura(Long id) {
        Factura facturaBD = this.findById(id);
        if (facturaBD != null) {
            facturaBD.setEstado("Deleted");
            facturaRepository.save(facturaBD);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Factura> findByCustomerId(Long customerid) {
        return facturaRepository.findByCustomerId(customerid);
    }
}
