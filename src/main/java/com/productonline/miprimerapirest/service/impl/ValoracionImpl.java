package com.productonline.miprimerapirest.service.impl;

import com.productonline.miprimerapirest.model.dao.ValoracionDao;
import com.productonline.miprimerapirest.model.dto.ValoracionDto;
import com.productonline.miprimerapirest.model.dto.ValoracionResponseDto;
import com.productonline.miprimerapirest.model.entity.Valoracion;
import com.productonline.miprimerapirest.service.IValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ValoracionImpl implements IValoracion {

    @Autowired
    private ValoracionDao valoracionDao;

    @Override
    public Valoracion agregarValoracion(ValoracionDto dto) {
        Valoracion valoracion = new Valoracion();
        valoracion.setCedula(dto.getCedula());
        valoracion.setIdProducto(dto.getIdProducto());
        valoracion.setPuntuacion(dto.getPuntuacion());
        return valoracionDao.save(valoracion);
    }

    @Override
    public List<ValoracionResponseDto> obtenerPorPuntuacion(Integer puntuacion) {

        List<Valoracion> valoraciones = valoracionDao.findByPuntuacion(puntuacion);

        if (valoraciones.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay valoraciones con esa puntuación.");
        }
        return valoraciones.stream()
                .map(v -> new ValoracionResponseDto(
                        v.getUsuario().getNombre(),
                        v.getProducto().getNombre(),
                        v.getPuntuacion()))
                .toList();


    }
}
