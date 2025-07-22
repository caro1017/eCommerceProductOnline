package com.productonline.miprimerapirest.service;

import java.util.List;

import com.productonline.miprimerapirest.model.dto.ComentarioDto;
import com.productonline.miprimerapirest.model.dto.ComentarioResponseDto;
import com.productonline.miprimerapirest.model.entity.Comentario;
import org.springframework.transaction.annotation.Transactional;

public interface IComentario {

    List<Comentario> obtenerComentariosPorProducto(Integer idProducto);

    @Transactional
    List<ComentarioResponseDto> obtenerComentariosPorProductoDto(Integer idProducto);

    List<ComentarioResponseDto> obtenerComentariosPorUsuario(String cedula);

    Comentario agregarComentario(ComentarioDto comentario);

    void eliminarComentario(Integer idComentario);

}
