package com.productonline.miprimerapirest.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComentarioResponseDto {
    private String nombreUsuario;
    private String textoComentario;
    private LocalDateTime fechaComentario;
    private String nombreProducto;
}

