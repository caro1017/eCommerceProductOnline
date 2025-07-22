package com.productonline.miprimerapirest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Valoracion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idValoracion;

    private Integer puntuacion;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "idProducto")
    private Integer idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cedula", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", insertable = false, updatable = false)
    private Producto producto;
}