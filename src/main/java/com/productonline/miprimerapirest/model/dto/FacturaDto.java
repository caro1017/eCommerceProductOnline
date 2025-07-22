package com.productonline.miprimerapirest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
@Builder
public class FacturaDto implements Serializable {
    private String cedula;
    private String direccionEnvio;
    private Integer idMetodoPago;
    private Integer idMetodoEnvio;
}