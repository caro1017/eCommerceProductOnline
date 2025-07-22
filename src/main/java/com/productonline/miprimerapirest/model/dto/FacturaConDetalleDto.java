package com.productonline.miprimerapirest.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class FacturaConDetalleDto {
    private String cedula;
    private String direccionEnvio;
    private Integer idMetodoEnvio;
    private Integer idMetodoPago;

    private List<DetalleDto> detalles;

    @Data
    public static class DetalleDto {
        private int idProducto;
        private int cantidad;
        private double precio;
    }
}