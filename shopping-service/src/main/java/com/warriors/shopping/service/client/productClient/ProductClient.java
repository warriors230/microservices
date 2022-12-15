package com.warriors.shopping.service.client.productClient;

import com.warriors.shopping.service.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service",path = "api/productos")

public interface ProductClient {
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Producto> traerProducto(@PathVariable(value = "id") Long id);

    @GetMapping("/actualizarStock/{id}")
    public ResponseEntity<Producto> actualizarStock(@PathVariable(value = "id") Long id,
                                                    @RequestParam(name = "quantity", required = false) Double quantity);
}
