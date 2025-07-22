package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UsuarioDao extends CrudRepository<Usuario,String> {

    Optional<Usuario> findByUsuario(String usuario);
}
