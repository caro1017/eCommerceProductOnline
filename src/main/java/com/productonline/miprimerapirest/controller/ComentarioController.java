package com.productonline.miprimerapirest.controller;

import com.productonline.miprimerapirest.model.dto.ComentarioDto;
import com.productonline.miprimerapirest.model.dto.ComentarioResponseDto;
import com.productonline.miprimerapirest.model.entity.Comentario;
import com.productonline.miprimerapirest.service.IComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ComentarioController {

    @Autowired
    private IComentario comentarioService;

//    @GetMapping("/comentariosProducto/{idProducto}")
//    public ResponseEntity<List<Comentario>> obtenerComentariosPorProducto(@PathVariable Integer idProducto) {
//        return ResponseEntity.ok(comentarioService.obtenerComentariosPorProducto(idProducto));
//    }

    @GetMapping("/comentariosProducto/{idProducto}")
    public ResponseEntity<List<ComentarioResponseDto>> obtenerComentariosPorProducto(@PathVariable Integer idProducto) {
        return ResponseEntity.ok(comentarioService.obtenerComentariosPorProductoDto(idProducto));
    }

//    @GetMapping("/comentariosUsuario/{cedula}")
//    public ResponseEntity<List<Comentario>> obtenerComentariosPorUsuario(@PathVariable String cedula) {
//        return ResponseEntity.ok(comentarioService.obtenerComentariosPorUsuario(cedula));
//    }

    @GetMapping("/comentariosUsuario/{cedula}")
    public ResponseEntity<List<ComentarioResponseDto>> obtenerComentariosPorUsuario(@PathVariable String cedula) {
        return ResponseEntity.ok(comentarioService.obtenerComentariosPorUsuario(cedula));
    }

    @PostMapping("/agregarComentario")
    public ResponseEntity<String> agregarComentario(@RequestBody ComentarioDto comentarioDto) {
        Comentario comentario = comentarioService.agregarComentario(comentarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comentario agregado con éxito");
    }

    @DeleteMapping("/eliminarComentario/{idComentario}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Integer idComentario) {
        comentarioService.eliminarComentario(idComentario);
        return ResponseEntity.noContent().build();
    }
}
