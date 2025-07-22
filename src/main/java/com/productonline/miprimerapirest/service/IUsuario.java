package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.dto.UsuarioDto;
import com.productonline.miprimerapirest.model.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

public interface IUsuario {

    Usuario save(UsuarioDto usuario);

    Usuario findById(String cedula);

    @Transactional(readOnly = true)
    boolean existsById(Integer cedula);

    @Transactional(readOnly = true)
    Usuario findById(Integer id);

    void delete(Usuario usuario);

    boolean existsById(String cedula);

    Usuario findByUsuario(String usuario);
}
