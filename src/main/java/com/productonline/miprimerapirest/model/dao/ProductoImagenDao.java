package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Producto;
import com.productonline.miprimerapirest.model.entity.ProductoImagen;
import org.springframework.data.repository.CrudRepository;

public interface ProductoImagenDao extends CrudRepository<ProductoImagen,Integer> {
    void deleteByProductoIdproducto(Integer idproducto);

    //void deleteByProductoId(Integer iproducto);
}
