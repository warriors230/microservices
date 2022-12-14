package com.warriors.microservicios.controller;

import com.warriors.microservicios.entities.Categoria;
import com.warriors.microservicios.entities.Producto;
import com.warriors.microservicios.repository.categorias.ICategoriaRepository;
import com.warriors.microservicios.services.IProductoService;
import com.warriors.microservicios.utils.ErrorMessageFormater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Slf4j
public class ProductoController {
    @Autowired
    IProductoService productoService;
    @Autowired
    ICategoriaRepository categoriaRepository;
    ErrorMessageFormater errorMessageFormater = new ErrorMessageFormater();

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Producto>> listarTodos(@RequestParam(name = "categoriaId", required = false) Long categoriaId)
            throws RuntimeException {

        List<Producto> productos;

        if (categoriaId == null) {
            productos = productoService.listarTodosLosProductos();

            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

        } else {
            productos = productoService.buscarPorCategoria(Categoria.builder().idCategoria(categoriaId).build());

            if (productos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }


        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Producto> traerProducto(@PathVariable(value = "id") Long id)
            throws RuntimeException {

        Producto productoBD = productoService.traerProducto(id);

        if (productoBD != null) {
            return ResponseEntity.ok(productoBD);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto, BindingResult result)
            throws RuntimeException {
        try {
            if (result.hasErrors()) {
                throw new RuntimeException(errorMessageFormater.formatMessage(result));
            }
            productoService.crearProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(producto);
        } catch (RuntimeException ex) {
            log.error("Ocurrio un error en el Sistema {}", ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable(value = "id") Long id, @RequestBody Producto producto)
            throws RuntimeException {
        producto.setIdProducto(id);
        Producto productoBD = productoService.actualizarProducto(producto);
        if (productoBD == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(productoBD);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable(name = "id") Long id)
            throws RuntimeException {
        Producto productoBD = productoService.traerProducto(id);
        if (productoBD == null) {
            return ResponseEntity.noContent().build();
        }
        productoBD = productoService.eliminarProducto(id);
        return ResponseEntity.ok().body(productoBD);
    }

    @GetMapping("/actualizarStock/{id}")
    public ResponseEntity<Producto> actualizarStock(@PathVariable(value = "id") Long id,
                                                    @RequestParam(name = "quantity", required = false) Double quantity)
            throws RuntimeException {
        Producto productoBD = productoService.actualizarStock(id, quantity);
        if (productoBD == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(productoBD);
    }
}
