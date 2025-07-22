package com.productonline.miprimerapirest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionResponseDto {
    private String nombreUsuario;
    private String nombreProducto;
    private Integer puntuacion;
}