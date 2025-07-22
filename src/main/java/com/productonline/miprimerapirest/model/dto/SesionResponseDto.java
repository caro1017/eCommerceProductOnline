package com.productonline.miprimerapirest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SesionResponseDto implements Serializable {

    private String usuario;
    private String mensaje;
    private String token;

}
