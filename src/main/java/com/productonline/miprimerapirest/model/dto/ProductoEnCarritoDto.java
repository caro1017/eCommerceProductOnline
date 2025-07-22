package com.productonline.miprimerapirest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEnCarritoDto {
    private Integer idProducto;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
}


