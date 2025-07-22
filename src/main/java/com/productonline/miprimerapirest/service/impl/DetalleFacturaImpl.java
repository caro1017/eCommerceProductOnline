package com.productonline.miprimerapirest.service.impl;

import com.productonline.miprimerapirest.model.dao.DetalleFacturaDao;
import com.productonline.miprimerapirest.model.dto.DetalleFacturaDto;
import com.productonline.miprimerapirest.model.entity.DetalleFactura;
import com.productonline.miprimerapirest.service.IDetalleFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleFacturaImpl implements IDetalleFactura {

    @Autowired
    private DetalleFacturaDao detalleFacturaDao;

    @Override
    public DetalleFactura crearDetalle(DetalleFacturaDto dto) {
        DetalleFactura detalle = new DetalleFactura();
        detalle.setNumeroPedido(dto.getNumeroPedido());
        detalle.setIdFactura(dto.getIdFactura());
        detalle.setIdProducto(Integer.valueOf(dto.getIdProducto()));
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecio(dto.getPrecio());
        return detalleFacturaDao.save(detalle);
    }

    @Override
    public List<DetalleFactura> obtenerPorFactura(String idFactura) {
        return detalleFacturaDao.findByFactura_IdFactura(idFactura);
    }
}