package com.productonline.miprimerapirest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
@Builder
public class ComentarioDto implements Serializable {

    private String cedula;
    private Integer idProducto;
    private String textoComentario;
}


