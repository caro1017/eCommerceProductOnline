package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoDao extends CrudRepository<Producto,Integer> {

    //List<Producto> findByCedula(String cedula);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByUsuario_Cedula(String cedula);
}
