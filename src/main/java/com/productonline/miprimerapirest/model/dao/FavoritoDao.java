package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Favorito;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FavoritoDao extends CrudRepository<Favorito, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Favorito f WHERE f.producto.idproducto = :idProducto")
    void deleteByIdProducto(@Param("idProducto") Integer idProducto);
}
