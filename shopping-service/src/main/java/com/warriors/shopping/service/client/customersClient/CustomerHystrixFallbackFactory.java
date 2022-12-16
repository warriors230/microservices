package com.warriors.shopping.service.client.customersClient;

import com.warriors.shopping.service.models.Customers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient{
    @Override
    public ResponseEntity<Customers> findById(Long id) {
        Customers customers = Customers.builder()
                .firtsName("none")
                .lastName("none")
                .numberID("none")
                .email("none")
                .photoUrl("none")
                .build();
        return ResponseEntity.ok(customers);
    }
}
