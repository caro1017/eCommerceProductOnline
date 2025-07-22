package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Carrito;
import com.productonline.miprimerapirest.model.entity.Producto;
import com.productonline.miprimerapirest.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CarritoDao extends CrudRepository<Carrito, Integer> {

    Optional<Carrito> findByUsuarioAndProducto(Usuario usuario, Producto producto);

    //void deleteByUsuarioCedula(String cedula);
    void deleteByUsuario_Cedula(String cedula);

    Optional<Carrito> findByUsuario_CedulaAndProducto_Idproducto(String cedula, Integer idproducto);

    List<Carrito> findByUsuario_Cedula(String cedula);
}
