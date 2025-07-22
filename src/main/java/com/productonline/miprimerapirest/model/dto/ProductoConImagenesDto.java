package com.productonline.miprimerapirest.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductoConImagenesDto implements Serializable {

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer idcategoria;
    private String cedula;
    private String referencia;
    private Integer stock;
    private String talla;
    private String marca;
    private String genero;
    private String color;
    private String garantia;
    private List<String> urls;
}