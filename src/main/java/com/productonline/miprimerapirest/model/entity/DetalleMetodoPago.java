package com.productonline.miprimerapirest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "DetalleMetodoPago")
public class DetalleMetodoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMetodoPagoDetalle")
    private Integer idMetodoPagoDetalle;

    @ManyToOne
    @JoinColumn(name = "IdMetodoPago", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "NombreTitular", nullable = false, length = 100)
    private String nombreTitular;

    @Column(name = "CedulaTitular", nullable = false, length = 20)
    private String cedulaTitular;

    @Column(name = "Franquicia", nullable = false, length = 50)
    private String franquicia;

    @Column(name = "Ultimos4Digitos", nullable = false, length = 4)
    private String ultimos4Digitos;

    @Column(name = "Banco", nullable = false, length = 100)
    private String banco;
}


