package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.entity.Carrito;
import java.math.BigDecimal;
import java.util.List;

public interface ICarrito {

    Carrito agregarProductoAlCarrito(String cedula, Integer idProducto, Integer cantidad);

    void vaciarCarrito(String cedula);

    Carrito actualizarCantidadProducto(String cedula, Integer idProducto, Integer nuevaCantidad);

    void eliminarProductoDelCarrito(String cedulaUsuario, Integer idProducto);

    List<Carrito> obtenerCarritoPorUsuario(String cedula);

    BigDecimal calcularTotal(List<Carrito> carrito);
}
