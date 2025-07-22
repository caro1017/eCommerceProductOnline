package com.productonline.miprimerapirest.controller;

import com.productonline.miprimerapirest.model.dto.ValoracionDto;
import com.productonline.miprimerapirest.model.dto.ValoracionResponseDto;
import com.productonline.miprimerapirest.model.entity.Valoracion;
import com.productonline.miprimerapirest.service.IValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ValoracionController {

    @Autowired
    private IValoracion valoracionService;

    @PostMapping("/agregar/valoracion")
    public ResponseEntity<String> agregarValoracion(@RequestBody ValoracionDto dto) {
        valoracionService.agregarValoracion(dto);
        return ResponseEntity.ok("Valoración agregada correctamente");
    }

    @GetMapping("/valoraciones/puntuacion/{puntuacion}")
    public ResponseEntity<List<ValoracionResponseDto>> obtenerPorPuntuacion(@PathVariable Integer puntuacion) {
        return ResponseEntity.ok(valoracionService.obtenerPorPuntuacion(puntuacion));
    }
}