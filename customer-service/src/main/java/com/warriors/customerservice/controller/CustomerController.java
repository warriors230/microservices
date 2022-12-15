package com.warriors.customerservice.controller;

import com.warriors.customerservice.entities.Customers;
import com.warriors.customerservice.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customers>> findAll() {
        List<Customers> founds = customerService.findAll();
        if (founds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(founds);
    }

    @PostMapping
    public ResponseEntity<Customers> save(@RequestBody Customers customers) throws RuntimeException {
        try {
            Customers customersNew = customerService.save(customers);
            return ResponseEntity.status(HttpStatus.CREATED).body(customersNew);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al momento de insertar el registro " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customers> update(@PathVariable("id") Long id, @RequestBody Customers customers) throws RuntimeException {
        try {
            Customers customersBD = customerService.update(id, customers);
            if (customersBD == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(customersBD);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al Actualizar " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws RuntimeException {
        try {
            Customers customersBD = customerService.findById(id);
            if (customersBD == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Customer no existe en el sistema!");
            }
            customerService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al Eliminar el Registro " + ex.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Customers> findById(@PathVariable(name = "id") Long id) throws RuntimeException {
        Customers customersDB = customerService.findById(id);
        if (customersDB != null) {
            return ResponseEntity.status(HttpStatus.OK).body(customersDB);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
