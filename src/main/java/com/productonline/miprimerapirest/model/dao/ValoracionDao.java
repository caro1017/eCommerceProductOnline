package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Valoracion;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ValoracionDao extends CrudRepository<Valoracion, Integer> {

    List<Valoracion> findByPuntuacion(Integer puntuacion);

    void deleteByProductoIdproducto(Integer idProducto);
}
