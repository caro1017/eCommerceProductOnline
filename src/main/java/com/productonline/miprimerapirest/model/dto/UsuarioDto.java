package com.productonline.miprimerapirest.model.dto;

import lombok.*;
import java.io.Serializable;

@Data
@ToString
@Builder
public class UsuarioDto implements Serializable {

    //private Integer usuarioId;
    private String cedula;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private String usuario;
    private String direccion;
    private String telefono;
    private String nacionalidad;

}


