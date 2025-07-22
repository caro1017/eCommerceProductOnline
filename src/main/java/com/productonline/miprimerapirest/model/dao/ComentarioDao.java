package com.productonline.miprimerapirest.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.productonline.miprimerapirest.model.entity.Comentario;
import java.util.List;

public interface ComentarioDao extends CrudRepository<Comentario, Integer> {

    List<Comentario> findByProducto_Idproducto(Integer idProducto);

    List<Comentario> findByUsuario_Cedula(String cedula);

}
