package com.productonline.miprimerapirest.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "Sesion")
public class Sesion implements Serializable {

    @Id
    @Column(name = "IdSesion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSesion;

    @Column(name = "Cedula")
    private String cedulaSesion;

    @Column(name = "FechaInicio")
    private Timestamp fechaInicio;

    @Column(name = "FechaFin")
    private Timestamp fechaFin;

    @Column(name = "Token")
    private String token;

    @Column(name = "Activa")
    private Integer activa;
}


