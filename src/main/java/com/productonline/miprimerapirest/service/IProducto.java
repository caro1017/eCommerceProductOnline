package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.dto.ProductoConImagenesDto;
import com.productonline.miprimerapirest.model.entity.Producto;

import java.util.List;

public interface IProducto {

//    Producto crearProductoConImagenes(Producto producto, List<String> urls);
    Producto crearProductoConImagenes(ProductoConImagenesDto dto);

    Producto updateProducto(Integer idProducto,ProductoConImagenesDto dto);

    Iterable<Producto> obtenerTodosLosProductos();

    List<Producto> obtenerProductosPorCedula(String cedula);

    List<Producto> buscarPorNombre(String nombre);

    void eliminarProductoPorId(Integer id);
}
