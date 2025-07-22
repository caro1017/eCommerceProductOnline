package com.productonline.miprimerapirest.controller;

import com.productonline.miprimerapirest.exception.ProductoNoEncontradoException;
import com.productonline.miprimerapirest.exception.UsuarioNoEncontradoException;
import com.productonline.miprimerapirest.model.dto.ProductoEnCarritoDto;
import com.productonline.miprimerapirest.model.entity.Carrito;
import com.productonline.miprimerapirest.model.entity.Producto;
import com.productonline.miprimerapirest.service.ICarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CarritoController {

    @Autowired
    private ICarrito carritoService;

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarProductoAlCarrito(@RequestParam String cedula,
                                                            @RequestParam Integer idProducto,
                                                            @RequestParam Integer cantidad) {
        try {
            Carrito carrito = carritoService.agregarProductoAlCarrito(cedula, idProducto, cantidad);
            //return ResponseEntity.ok(carrito.getIdCarrito());
            Map<String, Object> response = new HashMap<>();
            response.put("idCarrito", carrito.getIdCarrito());
            response.put("cantidad", carrito.getCantidad());
            response.put("nombreProducto", carrito.getProducto().getNombre());
            return ResponseEntity.ok(response);
        }
        catch (UsuarioNoEncontradoException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (ProductoNoEncontradoException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vaciar/{cedula}")
    public ResponseEntity<?> vaciarCarrito(@PathVariable String cedula) {
        try {
            carritoService.vaciarCarrito(cedula);
            return ResponseEntity.noContent().build();
        }
        catch (UsuarioNoEncontradoException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Error al Vacear el Carrito, Verificar que la cedula exista", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/actualizarcantidad")
//    public ResponseEntity<?> actualizarCantidadProducto(
//            @RequestParam String cedula,
//            @RequestParam Integer idproducto,
//            @RequestParam Integer nuevaCantidad) {
//        try {
//
//            // Llama al servicio para actualizar la cantidad
//            Carrito carritoActualizado = carritoService.actualizarCantidadProducto(cedula, idproducto, nuevaCantidad);
//
//            // Retorna la respuesta con el carrito actualizado
//            return ResponseEntity.ok(carritoActualizado);
//        }
//        catch (ProductoNoEncontradoException ex){
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
@PutMapping("/actualizarcantidad")
public ResponseEntity<?> actualizarCantidadProducto(
        @RequestParam String cedula,
        @RequestParam Integer idproducto,
        @RequestParam Integer nuevaCantidad) {
    try {
        Carrito carritoActualizado = carritoService.actualizarCantidadProducto(cedula, idproducto, nuevaCantidad);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("cantidadActualizada", carritoActualizado.getCantidad());

        return ResponseEntity.ok(respuesta);
    } catch (ProductoNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarProductoDelCarrito(@RequestParam String cedula, @RequestParam Integer idproducto) {
        carritoService.eliminarProductoDelCarrito(cedula, idproducto);
        return ResponseEntity.ok("Producto eliminado del carrito exitosamente.");
    }

//    @GetMapping("/Consultarcarrito/{cedula}")
//    public ResponseEntity<?> obtenerCarritoPorUsuario(@PathVariable String cedula) {
//        List<Carrito> carrito = carritoService.obtenerCarritoPorUsuario(cedula);
//        BigDecimal total = carritoService.calcularTotal(carrito);
//        Map<String, Object> respuesta = new HashMap<>();
//        respuesta.put("productos", carrito);
//        respuesta.put("total", total);
//        return ResponseEntity.ok(respuesta);
//    }

    @GetMapping("/Consultarcarrito/{cedula}")
    public ResponseEntity<?> obtenerCarritoPorUsuario(@PathVariable String cedula) {
        List<Carrito> carrito = carritoService.obtenerCarritoPorUsuario(cedula);
        BigDecimal total = carritoService.calcularTotal(carrito);

        List<ProductoEnCarritoDto> productos = carrito.stream().map(item -> {
            Producto p = item.getProducto();
            return new ProductoEnCarritoDto(p.getIdproducto(), p.getNombre(), p.getPrecio(), item.getCantidad());
        }).toList();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("productos", productos);
        respuesta.put("total", total);

        return ResponseEntity.ok(respuesta);
    }
}
