package com.productonline.miprimerapirest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@Builder
@ToString

public class SesionDto implements Serializable {

    private String usuario;
    private String password;

    public SesionDto(String cedula, String password) {
        this.usuario = usuario;
        this.password = password;
    }
}
