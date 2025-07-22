package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.dto.FacturaConDetalleDto;
import com.productonline.miprimerapirest.model.dto.FacturaDto;
import com.productonline.miprimerapirest.model.entity.Factura;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IFactura {
    Factura crearFactura(Factura factura);

    Factura crearFacturaDto(FacturaDto facturaDto);

    @Transactional
    Factura crearFacturaConDetalles(FacturaConDetalleDto dto);

    List<Factura> obtenerFacturas();

    Factura obtenerFacturaPorId(String id);

    void eliminarFactura(String id);
}
