package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.entity.Categoria;

public interface ICategoria {

    //Categoria save(Categoria producto);

    /*Producto findById(String cedula);

    @Transactional(readOnly = true)
    boolean existsById(Integer cedula);

    @Transactional(readOnly = true)
    Usuario findById(Integer id);

    void delete(Usuario usuario);

    boolean existsById(String cedula);*/
    boolean existsById(Integer idCategoria);
}
