package com.warriors.shopping.service.services.impl;

import com.warriors.shopping.service.client.customersClient.CustomerClient;
import com.warriors.shopping.service.client.productClient.ProductClient;
import com.warriors.shopping.service.entities.Factura;
import com.warriors.shopping.service.entities.FacturaItems;
import com.warriors.shopping.service.models.Producto;
import com.warriors.shopping.service.repository.IFacturaRepository;
import com.warriors.shopping.service.repository.IItemFacturaRepository;
import com.warriors.shopping.service.services.IFacturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FacturaService implements IFacturaService {

    @Autowired
    private IFacturaRepository facturaRepository;

    @Autowired
    IItemFacturaRepository itemFacturaRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    ProductClient productClient;

    @Transactional(readOnly = true)
    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Factura findById(Long id) {
        Factura facturaDB = facturaRepository.findById(id).orElse(null);
        if (facturaDB != null) {
            facturaDB.setCustomers(customerClient.findById(facturaDB.getCustomerId()).getBody());
        }
        List<FacturaItems> listaItems = facturaDB.getItems().stream().map(facturaItems -> {
            Producto producto = productClient.traerProducto(facturaItems.getIdProducto()).getBody();
            facturaItems.setProducto(producto);
            return facturaItems;
        }).collect(Collectors.toList());
        facturaDB.setItems(listaItems);
        return facturaDB;
    }

    @Transactional(readOnly = true)
    @Override
    public Factura findByNumeroFactura(String numeroFactura) {
        return facturaRepository.findByNumeroFactura(numeroFactura);
    }

    @Transactional
    @Override
    public Factura crearFactura(Factura factura) {
        factura.setEstado("CREATE");
        Factura facturaDB = facturaRepository.save(factura);
        facturaDB.getItems().forEach(facturaItems -> productClient.actualizarStock(facturaItems.getIdProducto(),
                facturaItems.getCantidad() * -1));
        facturaDB.getItems().forEach(facturaItems -> facturaItems.setProducto(productClient.traerProducto(facturaItems.getIdProducto()).getBody()));

        facturaDB.setCustomers(customerClient.findById(facturaDB.getCustomerId()).getBody());
        return facturaDB;
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
