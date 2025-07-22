package com.productonline.miprimerapirest.service.impl;

import com.productonline.miprimerapirest.exception.ProductoNoEncontradoException;
import com.productonline.miprimerapirest.exception.UsuarioNoEncontradoException;
import com.productonline.miprimerapirest.model.dao.CarritoDao;
import com.productonline.miprimerapirest.model.dao.ProductoDao;
import com.productonline.miprimerapirest.model.dao.UsuarioDao;
import com.productonline.miprimerapirest.model.entity.Carrito;
import com.productonline.miprimerapirest.model.entity.Producto;
import com.productonline.miprimerapirest.model.entity.Usuario;
import com.productonline.miprimerapirest.service.ICarrito;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoImpl implements ICarrito {

        @Autowired
        private CarritoDao carritoDao;

        @Autowired
        private UsuarioDao usuarioDao;

        @Autowired
        private ProductoDao productoDao;

        @Transactional
        public Carrito agregarProductoAlCarrito(String cedula, Integer idProducto, Integer cantidad) {
            if (cantidad == null || cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser un valor positivo.");
            }
            else {
                // Verificar si el usuario existe
                Usuario usuario = usuarioDao.findById(cedula)
                        .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con cédula: " + cedula));

                // Verificar si el producto existe
                Producto producto = productoDao.findById(idProducto)
                        .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado con ID: " + idProducto));

                // Crear o actualizar el carrito
                Carrito carrito = carritoDao.findByUsuarioAndProducto(usuario, producto)
                        .orElse(new Carrito());
                carrito.setUsuario(usuario);
                carrito.setProducto(producto);

                carrito.setCantidad((carrito.getCantidad() != null ? carrito.getCantidad() : 0) + cantidad);
                return carritoDao.save(carrito);
            }
        }

        @Transactional
        public void vaciarCarrito(String cedula) {
            Usuario usuario = usuarioDao.findById(cedula)
                    .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con cédula: " + cedula));
        carritoDao.deleteByUsuario_Cedula(cedula);
        }

        @Transactional
        public Carrito actualizarCantidadProducto(String cedula, Integer idproducto, Integer nuevaCantidad) {
        // Buscar el producto en el carrito del usuario
        Optional<Carrito> carritoOptional = carritoDao.findByUsuario_CedulaAndProducto_Idproducto(cedula, idproducto);

        if (!carritoOptional.isPresent()) {
            throw new ProductoNoEncontradoException("Producto no encontrado en el carrito para el usuario con cédula: " + cedula);
        }

        // Obtener el carrito y actualizar la cantidad
        Carrito carrito = carritoOptional.get();
        carrito.setCantidad(nuevaCantidad);

        // Guardar los cambios
        return carritoDao.save(carrito);
        }

        @Transactional
        public void eliminarProductoDelCarrito(String cedulaUsuario, Integer idProducto) {
        Optional<Carrito> carrito = carritoDao.findByUsuario_CedulaAndProducto_Idproducto(cedulaUsuario, idProducto);

        if (carrito.isPresent()) {
            carritoDao.delete(carrito.get());
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado en el carrito para el usuario con cédula: " + cedulaUsuario);
        }
    }

    @Transactional
    public List<Carrito> obtenerCarritoPorUsuario(String cedula) {
        return carritoDao.findByUsuario_Cedula(cedula);
    }

    public BigDecimal calcularTotal(@NotNull List<Carrito> carrito) {
        BigDecimal total = BigDecimal.ZERO;
        for (Carrito item : carrito) {
            total = total.add(item.getProducto().getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())));
        }
        return total;
    }
    }
