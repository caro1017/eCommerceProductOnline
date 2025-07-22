package com.productonline.miprimerapirest.controller;
import com.productonline.miprimerapirest.exception.CategoriaNoEncontradaException;
import com.productonline.miprimerapirest.exception.ProductoNoEncontradoException;
import com.productonline.miprimerapirest.exception.UsuarioNoEncontradoException;
import com.productonline.miprimerapirest.model.dto.ProductoConImagenesDto;
import com.productonline.miprimerapirest.model.entity.Producto;
import com.productonline.miprimerapirest.service.IProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class
ProductoController {

    @Autowired
    private IProducto productoService;

//    @PostMapping("/productos")
//    public ResponseEntity<?> crearProducto(@RequestBody Producto producto, @RequestParam List<String> urls) {
//        try {
//            Producto savedProducto = productoService.crearProductoConImagenes(producto, urls);
//            return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
//        } catch (UsuarioNoEncontradoException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }catch (CategoriaNoEncontradaException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
//        catch (Exception ex) {
//            return new ResponseEntity<>("Error al Agregar el Producto, Verificar que la Referencia del Producto sea Única", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/productos")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoConImagenesDto dto) {
        try {
            System.out.println("Cedulaaa " + dto.getCedula());
            Producto savedProducto = productoService.crearProductoConImagenes(dto);
            return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
        } catch (UsuarioNoEncontradoException | CategoriaNoEncontradaException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error al Agregar el Producto, Verificar que la Referencia del Producto sea Única", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/{idProducto}")
//    public ResponseEntity<Producto> updateProducto(@PathVariable Integer idProducto, @RequestBody Producto updatedProducto, @RequestParam List<String> urls) {
//        try {
//            Producto producto = productoService.updateProducto(idProducto, updatedProducto,urls);
//            return new ResponseEntity<>(producto, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

    @PutMapping("/update/{idProducto}")
    public ResponseEntity<?> updateProducto(@PathVariable Integer idProducto,@RequestBody ProductoConImagenesDto dto ){
        try {
            Producto productoActualizado = productoService.updateProducto(idProducto, dto);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (ProductoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        try {
            List<Producto> productos = (List<Producto>) productoService.obtenerTodosLosProductos();
            return ResponseEntity.ok(productos);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/cedula")
    public ResponseEntity<List<Producto>> obtenerProductosPorCedula(@RequestParam String cedula) {
        try {
            List<Producto> productos = productoService.obtenerProductosPorCedula(cedula);
            return ResponseEntity.ok(productos);
        }catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/nombre")
    public List<Producto> buscarPorNombre(@RequestParam String nombre) {
        try {
            List<Producto> productosNombres = productoService.buscarPorNombre(nombre);
            return ResponseEntity.ok(productosNombres).getBody();
        }catch (Exception ex){
            return (List<Producto>) new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
        try {
            productoService.eliminarProductoPorId(id);
            return ResponseEntity.ok().build();
        }catch (ProductoNoEncontradoException ex){
            throw ex;
        }
    }
}


