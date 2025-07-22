package com.productonline.miprimerapirest.service.impl;

import com.productonline.miprimerapirest.exception.UsuarioNoEncontradoException;
import com.productonline.miprimerapirest.model.dao.UsuarioDao;
import com.productonline.miprimerapirest.model.dto.UsuarioDto;
import com.productonline.miprimerapirest.model.entity.Usuario;
import com.productonline.miprimerapirest.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioImpl implements IUsuario {
    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public Usuario findById(Integer id) {
        return null;
    }

    @Autowired
    private UsuarioDao usuarioDao;
    //private Usuario usuario;

    @Transactional
    @Override
    public Usuario save(UsuarioDto usuarioDto) {
        Usuario usuario = null;
        usuario = usuario.builder()
                .cedula(usuarioDto.getCedula())
                .nombre(usuarioDto.getNombre())
                .apellido(usuarioDto.getApellido())
                .mail(usuarioDto.getMail())
                .password(usuarioDto.getPassword())
                .usuario(usuarioDto.getUsuario())
                .direccion(usuarioDto.getDireccion())
                .telefono(usuarioDto.getTelefono())
                .nacionalidad(usuarioDto.getNacionalidad())
                .build();
        return usuarioDao.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(String cedula) {
        return usuarioDao.existsById(cedula);
    }

    @Override
    public Usuario findByUsuario(String usuario) {

        return usuarioDao.findByUsuario(usuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con nombre de usuario: " + usuario));
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario findById(String cedula) {
        return usuarioDao.findById(cedula).orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no registrado"));
    }

    @Transactional
    @Override
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}
