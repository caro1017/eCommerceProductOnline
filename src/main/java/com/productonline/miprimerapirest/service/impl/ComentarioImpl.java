package com.productonline.miprimerapirest.service.impl;

import com.productonline.miprimerapirest.model.dao.ComentarioDao;
import com.productonline.miprimerapirest.model.dao.ProductoDao;
import com.productonline.miprimerapirest.model.dao.UsuarioDao;
import com.productonline.miprimerapirest.model.dto.ComentarioDto;
import com.productonline.miprimerapirest.model.dto.ComentarioResponseDto;
import com.productonline.miprimerapirest.model.entity.Comentario;
import com.productonline.miprimerapirest.model.entity.Producto;
import com.productonline.miprimerapirest.model.entity.Usuario;
import com.productonline.miprimerapirest.service.IComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ComentarioImpl implements IComentario {

    @Autowired
    private ComentarioDao comentarioDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private ProductoDao productoDao;

    @Override
    public List<Comentario> obtenerComentariosPorProducto(Integer idProducto) {
        return List.of();
    }

    @Override
    @Transactional
    public List<ComentarioResponseDto> obtenerComentariosPorProductoDto(Integer idProducto) {
        List<Comentario> comentarios = comentarioDao.findByProducto_Idproducto(idProducto);

        return comentarios.stream().map(comentario -> {
            ComentarioResponseDto dto = new ComentarioResponseDto();
            dto.setNombreUsuario(comentario.getUsuario().getUsuario());
            dto.setTextoComentario(comentario.getTextoComentario());
            dto.setFechaComentario(comentario.getFechaComentario());
            dto.setNombreProducto(comentario.getProducto().getNombre());
            return dto;
        }).toList();
    }

    @Override
    @Transactional
    public List<ComentarioResponseDto> obtenerComentariosPorUsuario(String cedula) {
        List<Comentario> comentarios = comentarioDao.findByUsuario_Cedula(cedula);

        return comentarios.stream().map(comentario -> {
            ComentarioResponseDto dto = new ComentarioResponseDto();
            dto.setNombreUsuario(comentario.getUsuario().getUsuario());
            dto.setTextoComentario(comentario.getTextoComentario());
            dto.setFechaComentario(comentario.getFechaComentario());
            dto.setNombreProducto(comentario.getProducto().getNombre());
            return dto;
        }).toList();
    }


//    @Override
//    @Transactional
//    public List<Comentario> obtenerComentariosPorUsuario(String cedula) {
//        return comentarioDao.findByUsuario_Cedula(cedula);
//    }

    @Override
    @Transactional
    public Comentario agregarComentario(ComentarioDto comentariodto) {

        Usuario usuario = usuarioDao.findById(comentariodto.getCedula())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoDao.findById(comentariodto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Comentario comentario = new Comentario();
        comentario.setUsuario(usuario);
        comentario.setProducto(producto);
        comentario.setTextoComentario(comentariodto.getTextoComentario());
        comentario.setFechaComentario(LocalDateTime.now());

        return comentarioDao.save(comentario);
    }

    @Override
    public void eliminarComentario(Integer idComentario) {
        Comentario comentario = comentarioDao.findById(idComentario)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con el ID: " + idComentario));
        comentarioDao.delete(comentario);
    }
}