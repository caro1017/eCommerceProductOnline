package com.productonline.miprimerapirest.service.impl;

import com.productonline.miprimerapirest.model.dao.*;
import com.productonline.miprimerapirest.model.dto.FacturaConDetalleDto;
import com.productonline.miprimerapirest.model.dto.FacturaDto;
import com.productonline.miprimerapirest.model.entity.DetalleFactura;
import com.productonline.miprimerapirest.model.entity.Factura;
import com.productonline.miprimerapirest.model.entity.MetodoEnvio;
import com.productonline.miprimerapirest.model.entity.MetodoPago;
import com.productonline.miprimerapirest.service.IFactura;
import com.productonline.miprimerapirest.service.IMetodoEnvio;
import com.productonline.miprimerapirest.service.IMetodoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FacturaImpl implements IFactura {

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private DetalleFacturaDao detalleFacturaDao;

    @Autowired
    private IMetodoPago metodoPagoRepository;

    @Autowired
    private IMetodoEnvio metodoEnvioRepository;

   @Autowired
   private UsuarioDao usuarioDao;

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    // Crear una nueva factura
    public Factura crearFactura(Factura factura) {
        return facturaDao.save(factura);
    }

    @Override
    public Factura crearFacturaDto(FacturaDto facturaDto) {
        Factura factura = new Factura();
        factura.setCedula(facturaDto.getCedula());
        factura.setDireccionEnvio(facturaDto.getDireccionEnvio());

        if (facturaDto.getIdMetodoPago() == null) {
            throw new IllegalArgumentException("idMetodoPago no puede ser null");
        }

        if (facturaDto.getIdMetodoEnvio() == null) {
            throw new IllegalArgumentException("idMetodoEnvio no puede ser null");
        }

        usuarioDao.findById(facturaDto.getCedula())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Relación con método de envío
        factura.setMetodoEnvio(
                metodoEnvioRepository.findById(facturaDto.getIdMetodoEnvio())
                        .orElseThrow(() -> new RuntimeException("Método de envío no encontrado"))
        );

        // Relación con método de pago
        factura.setMetodoPago(
                metodoPagoRepository.findById(facturaDto.getIdMetodoPago())
                        .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"))
        );

        return facturaDao.save(factura);
    }

    @Override
    @Transactional
    public Factura crearFacturaConDetalles(FacturaConDetalleDto dto) {
        // Obtener entidades relacionadas
        MetodoPago metodoPago = metodoPagoRepository.findById(dto.getIdMetodoPago())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(dto.getIdMetodoEnvio())
                .orElseThrow(() -> new RuntimeException("Método de envío no encontrado"));

        // Crear la factura
        Factura factura = new Factura();
        factura.setCedula(dto.getCedula());
        factura.setDireccionEnvio(dto.getDireccionEnvio());
        factura.setMetodoPago(metodoPago);
        factura.setMetodoEnvio(metodoEnvio);
        factura = facturaDao.save(factura); // Aquí se genera el idFactura y numeroPedido

        // Insertar detalles
        for (FacturaConDetalleDto.DetalleDto detalleDto : dto.getDetalles()) {
            // Verificar existencia del producto con el valor del DTO
            productoDao.findById(detalleDto.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto con ID " + detalleDto.getIdProducto() + " no existe"));
            DetalleFactura detalle = new DetalleFactura();
            //detalle.setIdFactura(factura.getIdFactura());
            detalle.setNumeroPedido(factura.getNumeroPedido());
            detalle.setIdProducto(detalleDto.getIdProducto());
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setPrecio(detalleDto.getPrecio());
            detalle.setFactura(factura); // Relación explícita
            detalleFacturaRepository.save(detalle);
        }
        return factura;
    }

    // Obtener todas las facturas
    public List<Factura> obtenerFacturas() {
        return (List<Factura>) facturaDao.findAll();
    }

    // Obtener una factura por su ID
    public Factura obtenerFacturaPorId(String id) {
        Optional<Factura> optionalFactura = facturaDao.findById(id);
        if (optionalFactura.isPresent()) {
            return optionalFactura.get();
        } else {
            throw new RuntimeException("Factura no encontrada con el ID: " + id);
        }
    }

    // Eliminar una factura
    public void eliminarFactura(String id) {
        detalleFacturaDao.deleteByIdFactura(id);
        facturaDao.deleteById(id);
    }

    }
