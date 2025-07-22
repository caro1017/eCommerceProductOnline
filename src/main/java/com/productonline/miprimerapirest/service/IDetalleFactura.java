package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.dto.DetalleFacturaDto;
import com.productonline.miprimerapirest.model.entity.DetalleFactura;
import java.util.List;

public interface IDetalleFactura {
    DetalleFactura crearDetalle(DetalleFacturaDto dto);
    List<DetalleFactura> obtenerPorFactura(String idFactura);
}
