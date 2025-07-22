package com.productonline.miprimerapirest.controller;

import com.productonline.miprimerapirest.model.dto.DetalleFacturaDto;
import com.productonline.miprimerapirest.model.entity.DetalleFactura;
import com.productonline.miprimerapirest.service.IDetalleFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DetalleFacturaController {

    @Autowired
    private IDetalleFactura detalleFacturaService;

    @PostMapping("/crear-detalle")
    public ResponseEntity<DetalleFactura> crear(@RequestBody DetalleFacturaDto dto) {
        return ResponseEntity.ok(detalleFacturaService.crearDetalle(dto));
    }

    @GetMapping("/detalle-por-factura/{idFactura}")
    public ResponseEntity<List<DetalleFactura>> obtener(@PathVariable String idFactura) {
        return ResponseEntity.ok(detalleFacturaService.obtenerPorFactura(idFactura));
    }
}