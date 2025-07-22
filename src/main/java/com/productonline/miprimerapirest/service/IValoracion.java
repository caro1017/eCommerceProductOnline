package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.dto.ValoracionDto;
import com.productonline.miprimerapirest.model.dto.ValoracionResponseDto;
import com.productonline.miprimerapirest.model.entity.Valoracion;
import java.util.List;

public interface IValoracion {
    Valoracion agregarValoracion(ValoracionDto dto);

    List<ValoracionResponseDto> obtenerPorPuntuacion(Integer puntuacion);
}