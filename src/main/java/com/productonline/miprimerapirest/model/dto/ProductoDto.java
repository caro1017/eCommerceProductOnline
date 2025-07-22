package com.productonline.miprimerapirest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
@Builder
public class ProductoDto implements Serializable {

    private Integer idproducto;
    private String nombre;
    private String descripcion;
    private float precio;
    private Integer idcategoria;
    private String cedula;
    private String referencia;
    private Integer stock;
    private String talla;
    private String marca;
    private String genero;
    private String color;
    private String garantia;
}


