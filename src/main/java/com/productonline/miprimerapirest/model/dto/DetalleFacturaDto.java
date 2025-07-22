package com.productonline.miprimerapirest.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DetalleFacturaDto implements Serializable {
    private String numeroPedido;
    private String idFactura;
    private String idProducto;
    private int cantidad;
    private double precio;
}
