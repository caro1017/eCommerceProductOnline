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
@Table(name = "Carrito")
public class Carrito implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarrito")
    private Integer idCarrito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cedula", referencedColumnName = "Cedula", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdProducto", referencedColumnName = "IdProducto", nullable = false)
    private Producto producto;

    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;
}

