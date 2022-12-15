package com.warriors.shopping.service.client;

import com.warriors.shopping.service.models.Customers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service",path = "/api/customers")
public interface CustomerClient {


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Customers> findById(@PathVariable(name = "id") Long id);
}
