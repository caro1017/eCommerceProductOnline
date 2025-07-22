package com.productonline.miprimerapirest.controller;

import com.productonline.miprimerapirest.model.dto.FacturaConDetalleDto;
import com.productonline.miprimerapirest.model.dto.FacturaDto;
import com.productonline.miprimerapirest.model.entity.Factura;
import com.productonline.miprimerapirest.service.IFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FacturaController {

    @Autowired
    private IFactura facturaService;

    // Crear una nueva factura
    @PostMapping(value = "/factura", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        Factura nuevaFactura = facturaService.crearFactura(factura);
        return ResponseEntity.ok(nuevaFactura);
    }

    //Crear una factura nueva por DTO
    @PostMapping("/crear-factura")
    public ResponseEntity<Factura> crearFactura(@RequestBody FacturaDto facturaDto) {
        Factura factura = facturaService.crearFacturaDto(facturaDto);
        return ResponseEntity.ok(factura);
    }

    @PostMapping("/crear-factura-con-detalles")
    public ResponseEntity<Factura> crearFacturaConDetalles(@RequestBody FacturaConDetalleDto dto) {
        Factura facturaCreada = facturaService.crearFacturaConDetalles(dto);
        return ResponseEntity.ok(facturaCreada);
    }

    // Obtener todas las facturas
    @GetMapping("/obtener-factura")
    public ResponseEntity<List<Factura>> obtenerFacturas() {
        List<Factura> facturas = facturaService.obtenerFacturas();
        return ResponseEntity.ok(facturas);
    }

    // Obtener una factura por su ID
    @GetMapping("obtener-factura/{id}")
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable String id) {
        Factura factura = facturaService.obtenerFacturaPorId(id);
        return ResponseEntity.ok(factura);
    }

    // Eliminar una factura por su ID
    @DeleteMapping("eliminar-factura/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable String id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }

    }
